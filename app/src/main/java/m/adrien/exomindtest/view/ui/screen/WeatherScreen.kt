package m.adrien.exomindtest.view.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import m.adrien.exomindtest.view.ui.element.WeatherTable
import m.adrien.exomindtest.view.ui.element.WeatherTopBar
import m.adrien.exomindtest.view.ui.event.LoadingEvent
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState
import m.adrien.exomindtest.view.viewModel.WeatherViewModel
import m.adrien.exomindtest.view.ui.element.WeatherBottom
import m.adrien.exomindtest.view.ui.element.WeatherErrorWarning

@Composable
fun WeatherScreen(
    onBackClick: () -> Unit, viewModel: WeatherViewModel
) {
    val loadingState by viewModel.loadingState.observeAsState(LoadingBarUiState.Waiting)
    val loadingMessage by viewModel.loadingMessage.observeAsState(null)
    val weatherState by viewModel.weatherListState.observeAsState(emptyList())
    val errorState by viewModel.errorUiState.observeAsState(null)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WeatherTopBar(onBackClick)

        WeatherTable(weatherState, modifier = Modifier.fillMaxWidth())

        //Le spacer push le composant suivant tout en bas de l'écran
        Spacer(modifier = Modifier.weight(1f))

        WeatherErrorWarning(errorState)

        WeatherBottom(
            onWeatherClick = { viewModel.onEvent(LoadingEvent.OnLoadingClick) },
            loadingFinishedListener = {
                viewModel.onEvent(
                    LoadingEvent.OnLoadingAnimationFinished(
                        it
                    )
                )
            },
            finishAnimationFinishedListener = { _ ->
                viewModel.onEvent(
                    LoadingEvent.OnLoadingCompletedAnimationFinished
                )
            },
            loadingState = loadingState,
            loadingMessage = loadingMessage,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

const val weather_screen_route = "weather_screen_route"