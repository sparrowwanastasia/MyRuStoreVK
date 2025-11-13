package com.example.myrustore.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Scheme = lightColorScheme(
    primary = ChipBlue,
    onPrimary = Color.White,
    background = AppBg,
    surface = Color.White,
    onSurface = Color(0xFF101828)
)

@Composable
fun MyRuStoreTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Scheme, content = content)
}

