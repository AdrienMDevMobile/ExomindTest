package m.adrien.exomindtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherJsonResponse(
    val id: Int
)

/*
@Serializable
data class WeatherJsonMain(
    val temp: Float,
    @SerialName("feels_like")
    val feelsLike: Float,
)*/