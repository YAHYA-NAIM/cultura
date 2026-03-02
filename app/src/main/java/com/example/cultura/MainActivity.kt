package com.example.cultura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cultura.navigation.NavGraph
import com.example.cultura.ui.theme.CulturaTheme
import com.example.cultura.viewmodel.SplashViewModel
import com.example.cultura.data.PreferencesManager
import com.example.cultura.viewmodel.SplashViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulturaTheme {
                // Get the SplashViewModel
                val splashViewModel: SplashViewModel = viewModel(
                    factory = SplashViewModelFactory(PreferencesManager(context = applicationContext))
                )

                // Set up the navigation
                val navController = rememberNavController()
                NavGraph(navController = navController, splashViewModel = splashViewModel)
            }
        }
    }
}
