package m.adrien.exomindtest.view.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import m.adrien.exomindtest.R

@Composable
fun WelcomeScreen(
    goToWeather: () -> Unit,
){
    Column {
        Text(
            stringResource(R.string.welcome_text)
        )
        Button(
            //TODO in a cleaner/more complex project :
            // should go through the Viewmodel with returns the request to goToWeather
            onClick = goToWeather,
        ) {
            Text(
                stringResource(R.string.navigate_to_weather)
            )
        }
    }
}

const val welcome_screen_route = "welcome_screen_route"