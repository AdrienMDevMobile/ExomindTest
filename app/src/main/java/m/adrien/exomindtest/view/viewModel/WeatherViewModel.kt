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
import m.adrien.exomindtest.domain.model.WeatherResponse
import m.adrien.exomindtest.view.ui.converter.toDrawable
import m.adrien.exomindtest.view.ui.uiState.WeatherErrorUiState
import m.adrien.exomindtest.view.ui.uiState.WeatherInfoUiState
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

    private val _weatherListState = MutableLiveData<List<WeatherInfoUiState>>(emptyList())
    val weatherListState: LiveData<List<WeatherInfoUiState>>
        get() = _weatherListState

    private val _errorUiState = MutableLiveData<WeatherErrorUiState?>(null)
    val errorUiState: LiveData<WeatherErrorUiState?>
        get() = _errorUiState

    fun onEvent(event: LoadingEvent) {
        when (event) {
            LoadingEvent.OnLoadingClick -> onLoadingClick()
            is LoadingEvent.OnLoadingAnimationFinished -> onLoadingAnimationFinished(event.value)
            LoadingEvent.OnLoadingCompletedAnimationFinished -> onLoadingCompletedAnimationFinished()
        }
    }

    private fun onLoadingClick() {
        viewModelScope.launch {
            _weatherListState.value = emptyList()

            _loadingState.value = LoadingBarUiState.Loading(0.0f)

            val loadingMessageFlow = getLoadingMessageFlow()

            callWeathers()

            loadingMessageFlow.cancel()
            _loadingMessage.value = null
        }
    }

    private fun getLoadingMessageFlow() = viewModelScope.launch {
        waitingMessageManager.getLoadingMessage().collect { newLoadingMessage ->
            _loadingMessage.value = newLoadingMessage
        }
    }

    private suspend fun callWeathers() {
        //TODO plutôt que ce soit le viewmodel qui boucle les localisations,
        // une autre option il faut plutôt que cela soit fait par le Datamanager.
        //Au retour du dernier message du data manager, le viewmodel éteindra tous les process
        var locationsCount = 0
        for (location in weatherLocations) {

            weatherDataManager.getWeather(location)
                .onSuccess {
                    addWeatherResponseIntoList(it)
                    _errorUiState.value = null
                }.onFailure {
                    _errorUiState.value = WeatherErrorUiState.Unknow_error
                    _loadingState.value = LoadingBarUiState.WaitRestart
                    return
                }

            locationsCount++
            _loadingState.value = LoadingBarUiState.Loading(
                locationsCount.toFloat() / weatherLocations.size
            )
            //TODO Le dernier delay fait bizzare, mais cela est dans l'énoncé
            // (la bar doit être chargée en 60 seconde, le dernier appel est fait au bout de 50 secondes)
            // dans ces situations, challenger la demande initiale
            delay(10000)
        }
    }

    private fun addWeatherResponseIntoList(weathers: WeatherResponse){
        //TODO : afin de minimiser les rafraichissement, il faut rentrer les deux météos en même temps
        // pour cela Creer une sous liste et l'ajotuer d'un coup
        for (weather in weathers.weathers) {
            _weatherListState.value = _weatherListState.value?.plus(
                WeatherInfoUiState(
                    weathers.location,
                    weathers.temperature,
                    weather.toDrawable()
                )
            )
        }
    }

    private fun onLoadingAnimationFinished(value: Float) {
        if (value == 1f) {
            _loadingState.value = LoadingBarUiState.Finished
        }
    }

    private fun onLoadingCompletedAnimationFinished() {
        _loadingState.value = LoadingBarUiState.WaitRestart
    }
}