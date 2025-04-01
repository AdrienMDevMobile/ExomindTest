package m.adrien.exomindtest.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import m.adrien.exomindtest.view.ui.screen.WeatherScreen
import m.adrien.exomindtest.view.ui.screen.WelcomeScreen
import m.adrien.exomindtest.view.ui.screen.weather_screen_route
import m.adrien.exomindtest.view.ui.screen.welcome_screen_route
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
                        startDestination = welcome_screen_route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(route = welcome_screen_route) {
                            WelcomeScreen { navController.navigate(weather_screen_route) }
                        }
                        composable(route = weather_screen_route) {
                            val viewModel = hiltViewModel<WeatherViewModel>()
                            WeatherScreen({ navController.popBackStack() }, viewModel)
                        }
                    }

                }
            }
        }
    }
}