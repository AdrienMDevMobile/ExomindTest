package m.adrien.exomindtest.data.managerimpl

import m.adrien.exomindtest.data.datasource.WeatherWebDataSource
import m.adrien.exomindtest.data.model.WeatherJsonResponse
import m.adrien.exomindtest.domain.datamanager.WeatherDataManager
import m.adrien.exomindtest.domain.model.WeatherLocation

class WeatherDataManagerWebImpl(
    val weatherWebDataSource: WeatherWebDataSource
): WeatherDataManager {
    //TODO Result<WeatherResponse>
    override suspend fun getWeather(location: WeatherLocation): WeatherJsonResponse {
        return weatherWebDataSource.callWeatherWeb(location.toString())
    }
}