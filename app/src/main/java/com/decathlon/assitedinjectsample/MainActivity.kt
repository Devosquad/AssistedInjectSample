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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.decathlon.assitedinjectsample.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = GameRoute(level = 10)
                    ) {
                        composable<GameRoute> { navBackStackEntry ->

                            val level: Int = navBackStackEntry.toRoute<GameRoute>().level

                            GameScreen(
                                title = "Game",
                                level = level,
                            )
                        }

                    }

                }
            }
        }
    }
}

@Serializable
data class GameRoute(val level: Int)


@Composable
fun GameScreen(
    title: String,
    level: Int,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = title,
            modifier = modifier,
        )
        Text(
            text = level.toString(),
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        GameScreen(
            title = "Android",
            level = 1
        )
    }
}
