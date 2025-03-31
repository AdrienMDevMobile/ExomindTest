package m.adrien.exomindtest.view.ui.loadingBar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m.adrien.exomindtest.view.ui.theme.ExomindTestTheme

@Composable
fun LoadingBar(
    state: LoadingBarUiState,
    loadingFinishedListener: ((Float) -> Unit)?,
    finishAnimationFinishedListener: ((Float) -> Unit)?,
    modifier: Modifier = Modifier,
    //TODO ne pas utiliser des couleurs en brute
    colorLoading: Color = Color.Red,
    colorFinished: Color = Color(0xFF009900),
) {
    Column(
        modifier = modifier
    ) {
        //Put here loading text
        Box(modifier = modifier
            .fillMaxWidth()
            //Ne pas mettre la couleur en dur
            .border(BorderStroke(5.dp, Color.Black))
            .padding(5.dp)
            .height(50.dp)) {
            when (state) {
                is LoadingBarUiState.Loading -> {
                    //TODO le message doit utiliser le catalogue de traduction

                    var progress by remember { mutableFloatStateOf(0f) }
                    val animatedProgress = animateFloatAsState(
                        targetValue = progress,
                        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                        finishedListener = loadingFinishedListener
                    ).value

                    ProgressIndicator(
                        progress = animatedProgress,
                        modifier = Modifier
                            .fillMaxSize(),
                        color = colorLoading,
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            //TODO Ne pas mettre les couleurs en dur
                            color = Color.White,
                            strokeWidth = 5.dp,
                        )
                    }

                    LaunchedEffect(key1 = state, block = {
                        while (progress < state.progress) {
                            val addition = progress + state.progress / 10
                            progress = if (addition > 1f) {
                                1f
                            } else {
                                addition
                            }
                        }
                    })
                }

                LoadingBarUiState.Finished -> {
                    var progress by remember { mutableFloatStateOf(0f) }
                    val animatedProgress = animateFloatAsState(
                        targetValue = progress,
                        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                        finishedListener = finishAnimationFinishedListener
                    ).value

                    ProgressIndicator(
                        progress = animatedProgress,
                        modifier = Modifier
                            .fillMaxSize(),
                        color = colorFinished,
                        isLeftToRight = false,
                        backgroundColor = colorLoading,
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CheckMarker(
                            animatedProgress,
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f),
                            color = Color.White,
                        )
                    }

                    LaunchedEffect(key1 = state, block = {
                        while (progress < 1f) {
                            val addition = progress + 1f / 10
                            progress = if (addition > 1f) {
                                1f
                            } else {
                                addition
                            }
                        }
                    })
                }

                LoadingBarUiState.Waiting -> {
                    //WaitingContent()
                }

                LoadingBarUiState.WaitRestart -> {
                    //WaitingContent()
                }
            }
        }

    }

}

/*
Les previews ne sont pas très bien gérés. La bar de chargement étant créée par l'animation, elle n'apparait pas dans la preview.
Ce qui oblige les tests à être visuels.
Je chercherai à optimiser cela si j'avais plus de temps.
 */

@Preview
@Composable
fun PreviewLoadingBar() {
    ExomindTestTheme {
        Surface(modifier = Modifier.size(200.dp)) {
            LoadingBar(
                state = LoadingBarUiState.Loading(
                    progress = 0.5f,
                ),
                loadingFinishedListener = {},
                finishAnimationFinishedListener = {}
            )
        }

    }
}

@Preview
@Composable
fun PreviewWaitingBar() {
    ExomindTestTheme {
        Surface(modifier = Modifier.size(200.dp)) {
            LoadingBar(
                state = LoadingBarUiState.Waiting,
                loadingFinishedListener = {},
                finishAnimationFinishedListener = {},
            )
        }
    }
}

@Preview
@Composable
fun PreviewFinishedBar() {
    ExomindTestTheme {
        Surface(modifier = Modifier.size(200.dp)) {
            LoadingBar(
                state = LoadingBarUiState.Finished,
                loadingFinishedListener = {},
                finishAnimationFinishedListener = {}
            )
        }
    }
}
