package m.adrien.exomindtest.view.ui.element

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import m.adrien.exomindtest.R
import m.adrien.exomindtest.domain.model.LoadingMessage
import m.adrien.exomindtest.view.ui.converter.toStringRes
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBar
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState

@Composable
fun WeatherBottom(
    onWeatherClick: () -> Unit,
    loadingFinishedListener: (Float) -> Unit,
    finishAnimationFinishedListener: (Float) -> Unit,
    loadingState: LoadingBarUiState,
    loadingMessage: LoadingMessage?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            stringResource(R.string.weather_arrival_message)
        )
        //TODO la logique de l'affichage ne devrait pas être gérée par la vue, mais par la viewmodel (uiState)
        if (loadingState is LoadingBarUiState.Waiting) {
            Button(
                onClick = {
                    onWeatherClick()
                },
            ) {
                Text(stringResource(R.string.weather_start))
            }
        }

        Text(loadingMessage?.toStringRes()?.let { stringResource(it) } ?: "")
        if (loadingState is LoadingBarUiState.WaitRestart) {
            Button(
                onClick = {
                    onWeatherClick()
                },
            ) {
                Text(stringResource(R.string.weather_restart))
            }
        } else {
            LoadingBar(
                state = loadingState,
                loadingFinishedListener = loadingFinishedListener,
                finishAnimationFinishedListener = finishAnimationFinishedListener,
                modifier = Modifier.fillMaxWidth()
            )
        }



    }
}