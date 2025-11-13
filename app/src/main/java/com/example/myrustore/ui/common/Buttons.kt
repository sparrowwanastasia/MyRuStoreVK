package com.example.myrustore.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myrustore.ui.theme.ChipBlue

@Composable
fun AppButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = ChipBlue,
        contentColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Row(Modifier.padding(horizontal = 18.dp, vertical = 12.dp)) {
            Text(text, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
fun AppButtonSmall(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = ChipBlue,
        contentColor = Color.White,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Row(Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(text, style = MaterialTheme.typography.labelMedium)
        }
    }
}
