package m.adrien.exomindtest.data.datasource

import android.util.Log
import kotlinx.serialization.json.Json
import m.adrien.exomindtest.data.model.WeatherJsonResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject


class WeatherWebDataSource @Inject constructor() {

    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    //Use Flavour to determin if it should set or not (for Prod)
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    /* Maquettage de l'appel reseau
    val rewrite_answer = Interceptor { chain ->
        val originalResponse: Response = chain.proceed(chain.request())
        originalResponse.newBuilder().body(
            """{"cod": 401, "message": "This is a test error answer"}""".toResponseBody(
                "application/json".toMediaTypeOrNull()
            )
        ).build()
    }*/
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(logging)
        //addInterceptor(rewrite_answer)
    }.build()

    private val appJson = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(appJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL).client(client).build()

    private val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    suspend fun callWeatherWeb(cityName: String): Result<WeatherJsonResponse> {
        Log.d("micheldr", "callWeatherWeb")
        try {
            val response = retrofitService.getWeather(cityName, WeatherApiKey().apiKey)
            Log.d("micheldr", "post response")
            return Result.success(response)
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }
}