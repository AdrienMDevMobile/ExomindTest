package m.adrien.exomindtest.domain.model

data class WeatherResponse(
    val location : String,
    val temperature: Float,
    val weather: List<Weather>,
)

enum class Weather {
    Clear,
    Clouds,
    Snow,
    Rain,
    Drizzle,
    Thunderstorm,
    Others
}