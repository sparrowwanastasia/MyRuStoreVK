package com.example.myrustore.ui.catalog

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.myrustore.ui.theme.ChipBlue
import com.example.myrustore.ui.theme.ChipGray
import com.example.myrustore.ui.theme.ChipIconBlue

@Composable
fun PillChip(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg by animateColorAsState(if (selected) ChipBlue else ChipGray, label = "bg")
    val contentColor by animateColorAsState(
        if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
        label = "fg"
    )
    val iconTint by animateColorAsState(if (selected) Color.White else ChipIconBlue, label = "icon")

    Surface(
        color = bg,
        contentColor = contentColor,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = iconTint)
            Spacer(Modifier.width(8.dp))
            Text(text, style = MaterialTheme.typography.labelLarge)
        }
    }
}
