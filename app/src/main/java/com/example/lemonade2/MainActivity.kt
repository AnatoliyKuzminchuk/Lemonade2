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
import com.example.lemonade2.R
import com.example.lemonade2.ui.theme.Lemonade2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade2Theme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {

    LemonadeProcess(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun LemonadeProcess(modifier: Modifier = Modifier
) {

    var result by remember {
        mutableStateOf(4)
    }
    val imageResource = when (result) {
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        3 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val StringResource = when (result) {
        1 -> R.string.lemon
        2 -> R.string.lemonade
        3 -> R.string.glass
        else -> R.string.lemon_tree
    }
    Surface(modifier = modifier,
        color = MaterialTheme.colors.background) {
        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = stringResource(id = StringResource), style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(id = imageResource), contentDescription = stringResource(id = StringResource),
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colors.secondary, MaterialTheme.shapes.medium)
                    .fillMaxSize(fraction = 0.5f)
                    .wrapContentSize()
                    .clickable {
                        if (result == 1) {
                            var temp = 1
                            temp = (1..4).random()
                            result = if (temp == 4) 2 else 1
                        } else if (result == 2) {
                            result = 3
                        } else if (result == 3) {
                            result = 4
                        } else {
                            result = 1
                        }
                    })

    }

    }
}