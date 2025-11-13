package com.example.myrustore.data.model

import androidx.annotation.DrawableRes

enum class Category { Финансы, Инструменты, Игры, Государственные, Транспорт }
enum class AgeRating { R0, R6, R8, R12, R16, R18 }

data class AppItem(
    val id: String,
    val iconUrl: String,                  // можно оставить пустым
    @DrawableRes val iconRes: Int? = null,// локальная иконка, если есть
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val developer: String,
    val category: Category,
    val age: AgeRating,
    val screenshots: List<String>
)

fun AgeRating.toText() = when (this) {
    AgeRating.R0 -> "0+"
    AgeRating.R6 -> "6+"
    AgeRating.R8 -> "8+"
    AgeRating.R12 -> "12+"
    AgeRating.R16 -> "16+"
    AgeRating.R18 -> "18+"
}
