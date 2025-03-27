package m.adrien.exomindtest.data.datasource

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import m.adrien.exomindtest.data.model.WeatherJsonResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class WeatherWebDataSource {

    //TODO : mieux injecter ces attributs là
    private val BASE_URL =
        "https://api.openweathermap.org/data/2.5/"
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client = OkHttpClient.Builder().apply { addInterceptor(logging) }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    suspend fun callWeatherWeb(cityName: String) :  WeatherJsonResponse{
        Log.d("micheldr","callWeatherWeb")
        val response = retrofitService.getWeather(cityName, WeatherApiKey().apiKey)
        Log.d("micheldr", "post response")
        return response
    }
}