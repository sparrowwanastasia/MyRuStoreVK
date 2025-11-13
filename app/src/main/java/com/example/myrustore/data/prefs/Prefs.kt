package com.example.myrustore.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private const val DS_NAME = "rustore_prefs"
val Context.dataStore by preferencesDataStore(DS_NAME)

object Keys {
    val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")
}

suspend fun setOnboardingDone(ctx: Context) {
    ctx.dataStore.edit { it[Keys.ONBOARDING_DONE] = true }
}

fun onboardingDoneFlow(ctx: Context) =
    ctx.dataStore.data.map { it[Keys.ONBOARDING_DONE] ?: false }
