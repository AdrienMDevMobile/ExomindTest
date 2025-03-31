package m.adrien.exomindtest.view.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import m.adrien.exomindtest.view.ui.event.LoadingEvent
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState

@Composable
fun WelcomeScreen(
    goToWeather: () -> Unit,
){
    Button(
        //TODO in a cleaner/more complex project :
        // should go through the Viewmodel with returns the request to goToWeather
        onClick = goToWeather,
    ) {
        Text(
            //TODO utiliser message catalogue
            "Navigue"
        )
    }
}