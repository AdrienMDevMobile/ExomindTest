package m.adrien.exomindtest.view.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import m.adrien.exomindtest.data.datasource.WeatherWebDataSource
import m.adrien.exomindtest.data.managerimpl.WeatherDataManagerWebImpl
import m.adrien.exomindtest.domain.datamanager.WeatherDataManager
import m.adrien.exomindtest.domain.model.WeatherLocation

class WeatherViewModel : ViewModel() {
    //fun getTestCall() {}

    //TODO
    val weatherDataManager : WeatherDataManager =
        WeatherDataManagerWebImpl(weatherWebDataSource = WeatherWebDataSource())


    fun getTestCall() {
        viewModelScope.launch {
            //marsUiState = MarsUiState.Loading
            //try {
                val result = weatherDataManager.getWeather(WeatherLocation.PARIS)
            Log.d("micheldr ", "viewmodel got result")
            /*} catch (e: Exception) {
                Log.d("micheldr", " viewmodel exception")
            }*/
        }
    }
}