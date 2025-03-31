package m.adrien.exomindtest.view.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import m.adrien.exomindtest.view.ui.element.WeatherTable
import m.adrien.exomindtest.view.ui.event.LoadingEvent
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBar
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState
import m.adrien.exomindtest.view.viewModel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel()
) {
    //utiliser hilt pour injecter le viewmodel
    //https://stackoverflow.com/questions/76051175/how-to-correctly-create-viewmodel-in-compose
    val loadingState by viewModel.loadingState.observeAsState(LoadingBarUiState.Waiting)
    val loadingMessage by viewModel.loadingMessage.observeAsState(null)
    val weatherState by viewModel.weatherListState.observeAsState(emptyList())


        Column {
            Button(
                onClick = {
                    viewModel.onEvent(LoadingEvent.OnLoadingClick)
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
            Text(loadingMessage?.toString() ?: "")
            LoadingBar(
                state = loadingState,
                loadingFinishedListener = {
                    viewModel.onEvent(
                        LoadingEvent.OnLoadingAnimationFinished(
                            it
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            WeatherTable(weatherState)
        }

}