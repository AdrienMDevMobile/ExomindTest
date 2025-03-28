package m.adrien.exomindtest.domain.datamanager

import m.adrien.exomindtest.domain.model.WeatherLocation
import m.adrien.exomindtest.domain.model.WeatherResponse

interface WeatherDataManager {
    suspend fun getWeather(location: WeatherLocation): Result<WeatherResponse>
}