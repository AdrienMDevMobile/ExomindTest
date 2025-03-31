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
import m.adrien.exomindtest.view.ui.event.LoadingEvent
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBar
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState

@Composable
fun WeatherBottom(
    onWeatherClick: () -> Unit,
    loadingFinishedListener : (Float) -> Unit,
    loadingState: LoadingBarUiState,
    loadingMessage: LoadingMessage?,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            stringResource(R.string.weather_arrival_message)
        )
        Button(
            onClick = {
                onWeatherClick()

            },
            //TODO la logique de enabled ne devrait pas être gérée par la vue, par la viewmodel (uiState)
            enabled = (loadingState is LoadingBarUiState.Waiting || loadingState is LoadingBarUiState.Finished)
        ) {
            Text(
                if (loadingState is LoadingBarUiState.Finished) {
                    //TODO utiliser message catalogue
                    "Recommencer"
                } else {
                    "click here"
                }
            )
        }
        Text(loadingMessage?.toStringRes()?.let { stringResource(it) } ?: "")
        LoadingBar(
            state = loadingState,
            loadingFinishedListener = loadingFinishedListener,
            modifier = Modifier.fillMaxWidth()
        )


    }
}