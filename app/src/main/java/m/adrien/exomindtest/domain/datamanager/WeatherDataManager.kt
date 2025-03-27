package m.adrien.exomindtest.domain.datamanager

import m.adrien.exomindtest.data.model.WeatherJsonResponse
import m.adrien.exomindtest.domain.model.WeatherLocation

interface WeatherDataManager {
    suspend fun getWeather(location: WeatherLocation): WeatherJsonResponse
}