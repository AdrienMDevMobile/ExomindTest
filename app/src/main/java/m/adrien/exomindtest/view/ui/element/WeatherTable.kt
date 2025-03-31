package m.adrien.exomindtest.view.ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import m.adrien.exomindtest.view.ui.uiState.WeatherInfoUiState

@Composable
fun WeatherTable(state: List<WeatherInfoUiState>){
    LazyColumn {
        state.forEach { weather ->
            item {
                //TODO faire un tableau plus joli
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Text(weather.name)
                    Text(weather.temperature.toString())
                    Icon(
                        painter = painterResource(weather.weather),
                        contentDescription = "TODO if you have time"
                    )
                }

            }
        }
    }
}