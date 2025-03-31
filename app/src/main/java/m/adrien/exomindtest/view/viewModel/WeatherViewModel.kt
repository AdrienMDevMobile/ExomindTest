package m.adrien.exomindtest.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.adrien.exomindtest.domain.datamanager.LoadingMessageManager
import m.adrien.exomindtest.domain.datamanager.WeatherDataManager
import m.adrien.exomindtest.domain.datamanager.WeatherLocationList
import m.adrien.exomindtest.view.ui.event.LoadingEvent
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState
import m.adrien.exomindtest.domain.model.LoadingMessage
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherDataManager: WeatherDataManager,
    private val waitingMessageManager: LoadingMessageManager
) : ViewModel() {

    /*
    fun getTestCall() {
        viewModelScope.launch {
            val result = weatherDataManager.getWeather(WeatherLocation.PARIS)
            result.onSuccess { success ->
                Log.d("micheldr ", "viewmodel got result" + success.weather.size + " " + success.location + success.temperature)
            }.onFailure {
                Log.d("micheldr", "Failure")
            }

        }
    }*/

    private val weatherLocations = WeatherLocationList().getWeatherLocationList()


    private val _loadingState = MutableLiveData<LoadingBarUiState>(LoadingBarUiState.Waiting)
    val loadingState: LiveData<LoadingBarUiState>
        get() = _loadingState

    private val _weatherListState = MutableLiveData<List<String>>(emptyList())
    val weatherListState: LiveData<List<String>>
        get() = _weatherListState

    fun onEvent(event: LoadingEvent) {
        when (event) {
            LoadingEvent.OnLoadingClick -> onLoadingClick()
            is LoadingEvent.OnLoadingAnimationFinished -> onLoadingAnimationFinished(event.value)
        }
    }

    private fun onLoadingClick() {
        viewModelScope.launch {
            _loadingState.value = LoadingBarUiState.Loading(0.0f, LoadingMessage.only_seconds)
            /*
            var locationsCount = 0
            for (location in weatherLocations) {
                _weatherListState.value = _weatherListState.value?.plus(location.toString())
                locationsCount++
                _loadingState.value = LoadingBarUiState.Loading(
                    locationsCount.toFloat() / weatherLocations.size,
                    LoadingMessage.only_seconds
                )
                delay(1000)
            }*/

            /*
            _loadingState.value = LoadingBarUiState.Loading(0.5f, LoadingMessage.only_seconds)
            delay(1000)
            _loadingState.value = LoadingBarUiState.Loading(0.7f, LoadingMessage.only_seconds)
            Log.d("loading", "0.7f")
            delay(1000)
            _loadingState.value = LoadingBarUiState.Loading(1f, LoadingMessage.only_seconds)
            Log.d("loading", "1f")*/

            waitingMessageManager.getLoadingMessage().collect { loadingMessage ->
                _loadingState.value = LoadingBarUiState.Loading(0.0f, loadingMessage)
            }
        }
    }

    private fun onLoadingAnimationFinished(value: Float) {
        if (value == 1f) {
            _loadingState.value = LoadingBarUiState.Finished
        }
    }
}