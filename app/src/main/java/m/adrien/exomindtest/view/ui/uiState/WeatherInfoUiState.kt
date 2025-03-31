package m.adrien.exomindtest.view.ui.uiState

import androidx.annotation.DrawableRes

data class WeatherInfoUiState(
    val name: String,
    val temperature: Float,
    @DrawableRes val weather: Int
)