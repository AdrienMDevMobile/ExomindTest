package m.adrien.exomindtest.view.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import m.adrien.exomindtest.view.ui.element.WeatherTable
import m.adrien.exomindtest.view.ui.element.WeatherTopBar
import m.adrien.exomindtest.view.ui.event.LoadingEvent
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState
import m.adrien.exomindtest.view.viewModel.WeatherViewModel
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import m.adrien.exomindtest.view.ui.element.WeatherBottom

@Composable
fun WeatherScreen(
    onBackClick: () -> Unit, viewModel: WeatherViewModel
) {
    //utiliser hilt pour injecter le viewmodel
    //https://stackoverflow.com/questions/76051175/how-to-correctly-create-viewmodel-in-compose
    val loadingState by viewModel.loadingState.observeAsState(LoadingBarUiState.Waiting)
    val loadingMessage by viewModel.loadingMessage.observeAsState(null)
    val weatherState by viewModel.weatherListState.observeAsState(emptyList())



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WeatherTopBar(onBackClick)


        WeatherTable(weatherState, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.weight(1f))

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
                    LoadingEvent.OnFinishAnimationFinished
                )
            },
            loadingState = loadingState,
            loadingMessage = loadingMessage,
            modifier = Modifier.fillMaxWidth()
        )

    }


}