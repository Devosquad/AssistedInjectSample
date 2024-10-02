package com.decathlon.assitedinjectsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.decathlon.assitedinjectsample.ui.theme.SampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val level = 1
                    val viewModel = hiltViewModel<GameViewModel>()
                    val gameEngineMessage by viewModel.gameEngineMessage

                    LaunchedEffect(key1 = Unit) {
                        viewModel.initGameEngine(level)
                        viewModel.startGame()
                    }

                    GameScreen(
                        title = "Welcome to the game",
                        gameEngineMessage = gameEngineMessage,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameScreen(title: String, gameEngineMessage: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = title,
            modifier = modifier
        )
        Text(
            text = gameEngineMessage,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleTheme {
        GameScreen("Android", "1")
    }
}