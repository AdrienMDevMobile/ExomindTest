package m.adrien.exomindtest.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import m.adrien.exomindtest.view.ui.screen.WeatherScreen
import m.adrien.exomindtest.view.ui.screen.WelcomeScreen
import m.adrien.exomindtest.view.ui.theme.ExomindTestTheme
import m.adrien.exomindtest.view.viewModel.WeatherViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ExomindTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController,
                        //TODO Ne pas mettre le nom des routes en dur comme cela.
                        startDestination = "Welcome",
                        Modifier.padding(innerPadding)
                    ) {
                        composable(route = "Welcome") {
                            WelcomeScreen({ navController.navigate("Weather") }
                            )
                        }
                        composable(route = "Weather") {
                            val viewModel = hiltViewModel<WeatherViewModel>()
                            WeatherScreen({ navController.popBackStack() }, viewModel)
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