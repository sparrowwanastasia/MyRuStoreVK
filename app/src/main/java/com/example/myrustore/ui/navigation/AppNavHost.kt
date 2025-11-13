package com.example.myrustore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myrustore.ui.catalog.CatalogScreen
import com.example.myrustore.ui.details.DetailsScreen
import com.example.myrustore.ui.onboarding.OnboardingScreen

@Composable
fun AppNavHost(nav: NavHostController, startDestination: String) {
    NavHost(navController = nav, startDestination = startDestination) {
        composable(Routes.Onboarding) {
            OnboardingScreen(onContinue = {
                nav.navigate(Routes.Catalog) {
                    popUpTo(Routes.Onboarding) { inclusive = true }
                }
            })
        }
        composable(Routes.Catalog) {
            CatalogScreen(onOpenDetails = { appId ->
                nav.navigate(Routes.details(appId))
            })
        }
        composable(Routes.Details) { backStack ->
            val appId = backStack.arguments?.getString("appId")!!
            DetailsScreen(appId = appId, onBack = { nav.popBackStack() })
        }
    }
}
