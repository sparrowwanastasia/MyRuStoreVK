package com.example.myrustore.ui.navigation

object Routes {
    const val Onboarding = "onboarding"
    const val Catalog = "catalog"
    const val Details = "details/{appId}"
    fun details(appId: String) = "details/$appId"
}
