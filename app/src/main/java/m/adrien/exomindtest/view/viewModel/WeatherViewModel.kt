package m.adrien.exomindtest.view.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.adrien.exomindtest.domain.datamanager.WeatherDataManager
import m.adrien.exomindtest.domain.model.WeatherLocation
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherDataManager : WeatherDataManager
): ViewModel() {

    fun getTestCall() {
        viewModelScope.launch {
            val result = weatherDataManager.getWeather(WeatherLocation.PARIS)
            result.onSuccess { success ->
                Log.d("micheldr ", "viewmodel got result" + success.weather.size + " " + success.location + success.temperature)
            }.onFailure {
                Log.d("micheldr", "Failure")
            }

        }
    }
}