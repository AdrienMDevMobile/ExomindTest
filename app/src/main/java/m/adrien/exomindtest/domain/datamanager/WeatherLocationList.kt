package m.adrien.exomindtest.domain.datamanager

import m.adrien.exomindtest.domain.model.WeatherLocation

class WeatherLocationList {
    fun getWeatherLocationList() = listOf(
        WeatherLocation.PARIS,
        WeatherLocation.LYON,
        WeatherLocation.NANTES,
        WeatherLocation.RENNES,
        WeatherLocation.BORDEAUX,
        WeatherLocation.STRASBOURG,
    )
}