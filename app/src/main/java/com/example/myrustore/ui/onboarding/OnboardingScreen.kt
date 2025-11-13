package com.example.myrustore.ui.onboarding


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import com.example.myrustore.data.prefs.setOnboardingDone

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Временно без лого
            Text("RuStore", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(12.dp))
            Text("Добро пожаловать в RuStore", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                scope.launch {
                    setOnboardingDone(ctx)
                    onContinue()
                }
            }) { Text("Перейти на витрину") }
        }
    }
}
