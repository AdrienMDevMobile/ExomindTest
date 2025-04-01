package m.adrien.exomindtest.view.ui.element

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import m.adrien.exomindtest.R
import m.adrien.exomindtest.view.ui.uiState.WeatherErrorUiState

@Composable
fun WeatherErrorWarning(error: WeatherErrorUiState?){
    //TODO faire une gestion plus fine des erreurs
    if(error != null){
        Text(
            text = stringResource(R.string.weather_error_unknown),
            color = Color.Red,
        )
    }
}