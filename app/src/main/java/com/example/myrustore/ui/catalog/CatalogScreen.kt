package com.example.myrustore.ui.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myrustore.data.local.LocalDataSource
import com.example.myrustore.data.model.AppItem
import com.example.myrustore.data.model.Category

/** Клик без ripple, чтобы строка не “мигала” */
private fun Modifier.noRippleClickable(onClick: () -> Unit) =
    this.clickable(
        indication = null,
        interactionSource = MutableInteractionSource(),
        onClick = onClick
    )

data class CatTab(val title: String, val category: Category?)

/** Экран каталога: сверху — горизонтальные пилюли категорий, снизу — предложения (фильтруются) */
@Composable
fun CatalogScreen(onOpenDetails: (String) -> Unit) {
    val allApps = LocalDataSource.apps

    val tabs = listOf(
        CatTab("Все", null),
        CatTab("Финансы", Category.Финансы),
        CatTab("Инструменты", Category.Инструменты),
        CatTab("Игры", Category.Игры),
        CatTab("Государственные", Category.Государственные),
        CatTab("Транспорт", Category.Транспорт),
    )
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val selectedCat = tabs[selectedIndex].category

    val apps = remember(allApps, selectedCat) {
        if (selectedCat == null) allApps else allApps.filter { it.category == selectedCat }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            // Белая панель с заголовком + рядом «пилюль»
            Surface(shadowElevation = 4.dp) {
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        "Витрина RuStore",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        items(tabs.size) { i ->
                            val tab = tabs[i]
                            PillChip(
                                text = tab.title,
                                icon = when (tab.category) {
                                    null -> Icons.Filled.Android
                                    Category.Финансы -> Icons.Filled.AccountBalance
                                    Category.Инструменты -> Icons.Filled.Build
                                    Category.Игры -> Icons.Filled.VideogameAsset
                                    Category.Государственные -> Icons.Filled.AccountBalance
                                    Category.Транспорт -> Icons.Filled.DirectionsBus
                                },
                                selected = i == selectedIndex,
                                onClick = { selectedIndex = i }
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        // Контент с учётом insets от Scaffold, скроллится полностью
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Text(
                    "Предложения",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
            items(apps) { app ->
                AppRow(app) { onOpenDetails(app.id) }
                Divider()
            }
            if (apps.isEmpty()) {
                item {
                    Text(
                        "Нет приложений в этой категории",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

/** Строка приложения в списке */
@Composable
private fun AppRow(app: AppItem, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .noRippleClickable(onClick)
    ) {
        // Если в данных указан локальный ресурс — показываем его, иначе — системную заглушку
        if (app.iconRes != null) {
            Image(
                painter = painterResource(app.iconRes),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Android, // заглушка-иконка
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
        }

        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(app.name, style = MaterialTheme.typography.titleMedium)
            Text(app.shortDescription, maxLines = 2, style = MaterialTheme.typography.bodyMedium)
            SmallPill(app.category.name)
        }
    }
}

/** Маленькая «пилюля»-бейдж (для категории в строке приложения) */
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
