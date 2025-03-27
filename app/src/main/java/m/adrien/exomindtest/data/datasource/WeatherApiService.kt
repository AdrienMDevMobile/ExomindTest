package m.adrien.exomindtest.data.datasource

import m.adrien.exomindtest.data.model.WeatherJsonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(@Query("cityName") location: String, @Query("appid") appid: String): WeatherJsonResponse
}