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
    val weatherDataManager : WeatherDataManager
): ViewModel() {

    fun getTestCall() {
        viewModelScope.launch {
            //marsUiState = MarsUiState.Loading
            //try {
            val result = weatherDataManager.getWeather(WeatherLocation.PARIS)
            Log.d("micheldr ", "viewmodel got result" + result.weather.size + " " + result.location + result.temperature)
            /*} catch (e: Exception) {
                Log.d("micheldr", " viewmodel exception")
            }*/
        }
    }
}