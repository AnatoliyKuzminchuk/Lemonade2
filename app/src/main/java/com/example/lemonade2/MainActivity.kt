package com.example.lemonade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade2.ui.theme.Lemonade2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade2Theme {
                var result by remember { mutableStateOf(4) }

                LemonadeProcess(
                    result = result,
                    onImageClick = {
                        result = getNextInstruction(result)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun LemonadeProcess(
    modifier: Modifier = Modifier,
    result: Int = 0,
    onImageClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(
                    id = when (result) {
                        1 -> R.string.lemon
                        2 -> R.string.lemonade
                        3 -> R.string.glass
                        else -> R.string.lemon_tree
                    }
                ),
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(
                    id = when (result) {
                        1 -> R.drawable.lemon_squeeze
                        2 -> R.drawable.lemon_drink
                        3 -> R.drawable.lemon_restart
                        else -> R.drawable.lemon_tree
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(fraction = 0.5f)
                    .wrapContentSize()
                    //.clip(RoundedCornerShape(topEnd = 20.dp, bottomStart = 20.dp))
                    .clickable(onClick = onImageClick)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.secondary,
                        shape = MaterialTheme.shapes.medium
                    )
            )
        }
    }
}

private fun getNextInstruction(
    result: Int
) = when (result) {
    1 -> {
        val temp: Int = (1..4).random()
        if (temp == 4) 2 else 1
    }
    2 -> 3
    3 -> 4
    else -> 1
}

@Preview
@Composable
private fun LemonadeApp() {
    Lemonade2Theme {
        var result by remember { mutableStateOf(4) }

        LemonadeProcess(
            result = result,
            onImageClick = {
                result = getNextInstruction(result)
            },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}