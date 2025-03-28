package m.adrien.exomindtest.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import m.adrien.exomindtest.view.ui.event.LoadingEvent
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBar
import m.adrien.exomindtest.view.ui.loadingBar.LoadingBarUiState
import m.adrien.exomindtest.view.ui.theme.ExomindTestTheme
import m.adrien.exomindtest.view.viewModel.WeatherViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            //utiliser hilt pour injecter le viewmodel
            //https://stackoverflow.com/questions/76051175/how-to-correctly-create-viewmodel-in-compose
            val loadingState by viewModel.loadingState.observeAsState(LoadingBarUiState.Waiting)
            val weatherState by viewModel.weatherListState.observeAsState(emptyList())

            ExomindTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Button(
                            onClick = {
                                //viewModel.getTestCall()
                                viewModel.onEvent(LoadingEvent.OnLoadingClick)
                            },
                            modifier = Modifier.padding(innerPadding),
                            enabled = (loadingState is LoadingBarUiState.Waiting)
                        ) {
                            Text("click here")
                        }
                        LoadingBar(
                            state = loadingState,
                            loadingFinishedListener = {
                                viewModel.onEvent(
                                    LoadingEvent.OnLoadingAnimationFinished(
                                        it
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        LazyColumn {
                            weatherState.forEach { weather ->
                                item {
                                    Text(weather)
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExomindTestTheme {
        Greeting("Android")
    }
}