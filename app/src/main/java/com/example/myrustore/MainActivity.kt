package com.example.myrustore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.myrustore.data.prefs.onboardingDoneFlow
import com.example.myrustore.ui.navigation.AppNavHost
import com.example.myrustore.ui.navigation.Routes
import com.example.myrustore.ui.theme.MyRuStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRuStoreTheme {
                val nav = rememberNavController()
                val done by onboardingDoneFlow(this@MainActivity).collectAsState(initial = false)
                val start = if (done) Routes.Catalog else Routes.Onboarding
                AppNavHost(nav = nav, startDestination = start)
            }
        }
    }
}
