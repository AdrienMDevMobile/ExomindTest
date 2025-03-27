package m.adrien.exomindtest.data.managerimpl

import m.adrien.exomindtest.data.converter.toDomain
import m.adrien.exomindtest.data.datasource.WeatherWebDataSource
import m.adrien.exomindtest.domain.datamanager.WeatherDataManager
import m.adrien.exomindtest.domain.model.WeatherLocation
import m.adrien.exomindtest.domain.model.WeatherResponse
import javax.inject.Inject

class WeatherDataManagerWebImpl @Inject constructor(
    val weatherWebDataSource: WeatherWebDataSource
): WeatherDataManager {
    //TODO L'idéal serait un système de result : qui inclue la possibilité d'une erreur
    //exemple : Result<WeatherResponse>
    override suspend fun getWeather(location: WeatherLocation): WeatherResponse {
        return weatherWebDataSource.callWeatherWeb(location.toString()).toDomain()
    }
}