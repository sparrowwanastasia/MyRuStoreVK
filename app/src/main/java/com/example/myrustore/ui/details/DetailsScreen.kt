package com.example.myrustore.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myrustore.data.local.LocalDataSource
import com.example.myrustore.data.model.toText
import com.example.myrustore.ui.common.AppButtonSmall

@Composable
fun DetailsScreen(appId: String, onBack: () -> Unit) {
    val app = LocalDataSource.appById(appId)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Surface(shadowElevation = 4.dp) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppButtonSmall("Назад", onClick = onBack)
                    Text(app.name, style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.width(48.dp))
                }
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row {
                if (app.iconRes != null) {
                    Image(
                        painter = painterResource(app.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(72.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Android,
                        contentDescription = null,
                        modifier = Modifier.size(72.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(app.name, style = MaterialTheme.typography.titleLarge)
                    Text(app.developer, style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(6.dp))
                    Row {
                        SmallPill(app.category.name)
                        Spacer(Modifier.width(8.dp))
                        SmallPill(app.age.toText())
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Скриншоты", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(if (app.screenshots.isEmpty()) List(3) { "" } else app.screenshots) {
                    Surface(
                        tonalElevation = 2.dp,
                        modifier = Modifier
                            .height(220.dp)
                            .fillParentMaxWidth(0.8f)
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Скриншот")
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Описание", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(app.longDescription)
        }
    }
}

@Composable
private fun SmallPill(text: String) {
    Surface(shape = MaterialTheme.shapes.small, tonalElevation = 1.dp) {
        Text(
            text,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
