package com.decathlon.assitedinjectsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                        startDestination = ListRoute
                    ) {

                        composable<ListRoute> {
                            ListScreen(
                                navigateToDetailRoute = {
                                    navController.navigate(DetailRoute(it))
                                }
                            )
                        }

                        composable<DetailRoute> { navBackStackEntry ->
                            val index: Int = navBackStackEntry.toRoute<DetailRoute>().index
                            DetailScreen(index)
                        }

                    }

                }
            }
        }
    }

    @Composable
    private fun DetailScreen(
        index: Int,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = emojiItems[index].emoji,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }

    @Composable
    private fun ListScreen(
        modifier: Modifier = Modifier,
        navigateToDetailRoute: (Int) -> Unit,
    ) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(
                key = { index, _ -> index },
                items = emojiItems
            ) { index, item ->
                ListItem(
                    modifier = Modifier.clickable {
                        navigateToDetailRoute(index)
                    },
                    headlineContent = {
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    leadingContent = {
                        Text(
                            text = item.emoji,
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                )
            }
        }
    }
}

@Serializable
object ListRoute

@Serializable
data class DetailRoute(val index: Int)
