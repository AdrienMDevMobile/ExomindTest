package m.adrien.exomindtest.view.ui.converter

import m.adrien.exomindtest.R
import m.adrien.exomindtest.domain.model.Weather

fun Weather.toDrawable() =
    when(this){
        Weather.Clear -> R.drawable.weather_clear
        Weather.Clouds -> R.drawable.weather_cloud
        Weather.Snow -> R.drawable.weather_snow
        Weather.Rain -> R.drawable.weather_rain
        Weather.Drizzle -> R.drawable.weather_drizzle
        Weather.Thunderstorm -> R.drawable.weather_thunderstorm
        Weather.Others -> R.drawable.weather_others
    }