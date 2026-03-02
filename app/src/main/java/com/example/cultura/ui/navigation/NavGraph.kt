package com.example.cultura.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cultura.ui.home.HomeScreen
import com.example.cultura.ui.onboarding.OnboardingScreen
import com.example.cultura.ui.splash.SplashScreen
import com.example.cultura.viewmodel.SplashViewModel

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
}

@Composable
fun NavGraph(navController: NavHostController, splashViewModel: SplashViewModel) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                splashViewModel = splashViewModel,
                onNavigateToHome = {
                    if (splashViewModel.isOnboardingComplete.value) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(onFinished = {
                splashViewModel.completeOnboarding() // Mark onboarding as complete
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}

