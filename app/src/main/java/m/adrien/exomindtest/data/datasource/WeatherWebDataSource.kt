package m.adrien.exomindtest.data.datasource

import android.util.Log
import kotlinx.serialization.json.Json
import m.adrien.exomindtest.data.model.WeatherJsonResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class WeatherWebDataSource {

    //TODO : mieux injecter ces attributs là
    private val BASE_URL =
        "https://api.openweathermap.org/data/2.5/"
    //Use Flavour to determin if it should set or not (for Prod)
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client = OkHttpClient.Builder().apply { addInterceptor(logging) }.build()

    private val appJson = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(appJson.asConverterFactory("application/json".toMediaType()))
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