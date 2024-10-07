package com.decathlon.assitedinjectsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
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

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    SharedTransitionLayout {
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
                                    },
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                    animatedContentScope = this@composable
                                )
                            }

                            composable<DetailRoute> { navBackStackEntry ->
                                val index: Int = navBackStackEntry.toRoute<DetailRoute>().index
                                DetailScreen(
                                    index = index,
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                    animatedContentScope = this@composable
                                )
                            }

                        }

                    }
                }
            }
        }
    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    private fun DetailScreen(
        index: Int,
        sharedTransitionScope: SharedTransitionScope,
        animatedContentScope: AnimatedContentScope
    ) {
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-$index"),
                            animatedVisibilityScope = animatedContentScope
                        ),
                    text = emojiItems[index].emoji,
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    private fun ListScreen(
        modifier: Modifier = Modifier,
        navigateToDetailRoute: (Int) -> Unit,
        sharedTransitionScope: SharedTransitionScope,
        animatedContentScope: AnimatedContentScope
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
                with(sharedTransitionScope) {
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
                                modifier = Modifier
                                    .sharedElement(
                                        sharedTransitionScope.rememberSharedContentState(key = "image-$index"),
                                        animatedVisibilityScope = animatedContentScope
                                    ),
                                text = item.emoji,
                                style = MaterialTheme.typography.displayMedium
                            )
                        }
                    )
                }
            }
        }
    }
}

@Serializable
object ListRoute

@Serializable
data class DetailRoute(val index: Int)
