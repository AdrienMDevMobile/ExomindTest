package m.adrien.exomindtest.data.converter

import m.adrien.exomindtest.domain.model.Weather

fun String.toWeather(): Weather = when (this) {
    "Clear" -> Weather.Clear
    "Clouds" -> Weather.Clouds
    "Snow" -> Weather.Snow
    "Rain" -> Weather.Rain
    "Drizzle" -> Weather.Drizzle
    "Thunderstorm" -> Weather.Thunderstorm
    else -> Weather.Others
}