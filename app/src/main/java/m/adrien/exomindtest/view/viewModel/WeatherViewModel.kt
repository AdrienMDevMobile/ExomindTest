package m.adrien.exomindtest.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private val weatherLocations = WeatherLocationList().getWeatherLocationList()

    //TODO avoir une seule classe UiState qui réunit les deux serait mieux, mais ici cela permet de gagner du temps
    private val _loadingState = MutableLiveData<LoadingBarUiState>(LoadingBarUiState.Waiting)
    val loadingState: LiveData<LoadingBarUiState>
        get() = _loadingState

    private val _loadingMessage = MutableLiveData<LoadingMessage?>(null)
    val loadingMessage: LiveData<LoadingMessage?>
        get() = _loadingMessage

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
            _weatherListState.value = emptyList()

            _loadingState.value = LoadingBarUiState.Loading(0.0f, LoadingMessage.only_seconds)

            val loadingMessageFlow = viewModelScope.launch {
                waitingMessageManager.getLoadingMessage().collect { newLoadingMessage ->
                    _loadingMessage.value = newLoadingMessage
                }
            }

            callWeathers()

            loadingMessageFlow.cancel()
            _loadingMessage.value = null
        }
    }

    private suspend fun callWeathers(){
        //TODO plutôt que ce soit le viewmodel qui boucle les localisations,
        // une autre option il faut plutôt que cela soit fait par le Datamanager.
        //Au retour du dernier message du data manager, le viewmodel éteindra tous les process
        var locationsCount = 0
        for (location in weatherLocations) {
            delay(10000)
            weatherDataManager.getWeather(location).onSuccess { weather ->
                _weatherListState.value = _weatherListState.value?.plus(weather.location + weather.weather.size + weather.temperature)
            }.onFailure {
                //TODO dans un vrai projet
            }

            locationsCount++

            _loadingState.value = LoadingBarUiState.Loading(
                locationsCount.toFloat() / weatherLocations.size,
                LoadingMessage.only_seconds
            )
        }
    }

    private fun onLoadingAnimationFinished(value: Float) {
        if (value == 1f) {
            _loadingState.value = LoadingBarUiState.Finished
        }
    }
}