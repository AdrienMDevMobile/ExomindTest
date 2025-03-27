package m.adrien.exomindtest.domain.model

data class WeatherResponse(
    val temperature: Float,
    val feels_like: Float,
    val humidity: Int
)