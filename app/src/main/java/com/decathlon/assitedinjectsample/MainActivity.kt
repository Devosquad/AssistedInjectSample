package com.decathlon.assitedinjectsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    @OptIn(
        ExperimentalSharedTransitionApi::class,
        ExperimentalMaterial3Api::class
    )
    @Composable
    private fun ListScreen(
        modifier: Modifier = Modifier,
        navigateToDetailRoute: (Int) -> Unit,
        sharedTransitionScope: SharedTransitionScope,
        animatedContentScope: AnimatedContentScope
    ) {

        HorizontalMultiBrowseCarousel(
            modifier = modifier.fillMaxSize(),
            state = rememberCarouselState { emojiItems.count() },
            preferredItemWidth = 60.dp,
            itemSpacing = 2.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { index ->

            with(sharedTransitionScope) {
                Text(
                    modifier = Modifier
                        .clickable { navigateToDetailRoute(index) }
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-$index"),
                            animatedVisibilityScope = animatedContentScope
                        ),
                    text = emojiItems[index].emoji,
                    style = MaterialTheme.typography.displayMedium
                )
            }

        }
    }
}

@Serializable
object ListRoute

@Serializable
data class DetailRoute(val index: Int)
