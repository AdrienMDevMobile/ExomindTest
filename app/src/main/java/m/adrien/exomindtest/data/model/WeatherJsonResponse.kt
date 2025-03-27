package m.adrien.exomindtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherJsonResponse(
    val name: String,
    val main: WeatherJsonTemperature,
    val weather : List<WeatherJsonWeather>
)


@Serializable
data class WeatherJsonTemperature(
    val temp: Float,
)

//pas ouf le nom...
@Serializable
data class WeatherJsonWeather(
    val main: String,
)