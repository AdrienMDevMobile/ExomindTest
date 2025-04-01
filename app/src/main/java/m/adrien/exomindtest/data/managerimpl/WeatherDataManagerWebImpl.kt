package m.adrien.exomindtest.data.managerimpl

import m.adrien.exomindtest.data.converter.toDomain
import m.adrien.exomindtest.data.datasource.WeatherWebDataSource
import m.adrien.exomindtest.domain.datamanager.WeatherDataManager
import m.adrien.exomindtest.domain.model.WeatherLocation
import m.adrien.exomindtest.domain.model.WeatherResponse
import javax.inject.Inject

class WeatherDataManagerWebImpl @Inject constructor(
    private val weatherWebDataSource: WeatherWebDataSource
): WeatherDataManager {
    override suspend fun getWeather(location: WeatherLocation): Result<WeatherResponse> {

        return weatherWebDataSource.callWeatherWeb(location.toString()).map { success ->
            success.toDomain()
        }
    }
}