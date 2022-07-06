package com.mitch.my_unibo.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mitch.my_unibo.ui.util.AppLanguage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

        val THEME_SETTING = intPreferencesKey("theme")
        val LANGUAGE_SETTING = stringPreferencesKey("language")
        val NOTIFICATIONS_SETTING = booleanPreferencesKey("notifications")
    }

    // get the saved themeId
    // returns 0 (Light Theme) as the default one
    suspend fun getThemeId(): Int =
        context.dataStore.data.map {preferences -> preferences[THEME_SETTING] ?: 0}.first()

    // save theme preference into datastore
    suspend fun saveTheme(themeId: Int) {
        context.dataStore.edit {preferences ->
            preferences[THEME_SETTING] = themeId
        }
    }

    suspend fun getLanguage(): AppLanguage {
        val languageTag =
            context.dataStore.data.map {preferences -> preferences[LANGUAGE_SETTING] ?: "it"}
                .first()

        return when (languageTag) {
            AppLanguage.Italian.languageTag -> AppLanguage.Italian
            AppLanguage.English.languageTag -> AppLanguage.English
            AppLanguage.Spanish.languageTag -> AppLanguage.Spanish
            else -> throw Exception()
        }
    }

    suspend fun saveLanguage(appLanguage: AppLanguage) {
        val languageTag = appLanguage.languageTag
        context.dataStore.edit {preferences ->
            preferences[LANGUAGE_SETTING] = languageTag
        }
    }

    suspend fun areNotificationsOn(): Boolean {
        return context
            .dataStore
            .data
            .map { preferences -> preferences[NOTIFICATIONS_SETTING] ?: true }
            .first()
    }

    suspend fun enableNotifications() {
        context.dataStore.edit {preferences -> preferences[NOTIFICATIONS_SETTING] = true}
    }

    suspend fun disableNotifications() {
        context.dataStore.edit {preferences -> preferences[NOTIFICATIONS_SETTING] = false}
    }
}
