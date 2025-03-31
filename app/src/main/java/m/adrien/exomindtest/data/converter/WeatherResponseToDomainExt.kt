package m.adrien.exomindtest.data.converter

import m.adrien.exomindtest.data.model.WeatherJsonResponse
import m.adrien.exomindtest.domain.model.WeatherResponse

fun WeatherJsonResponse.toDomain(): WeatherResponse =
    WeatherResponse(
        location = this.name,
        temperature = this.main.temp,
        weathers = this.weather.map {
            it.main.toWeather()
        })
