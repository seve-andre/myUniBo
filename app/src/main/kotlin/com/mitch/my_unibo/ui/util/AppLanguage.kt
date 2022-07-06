package com.mitch.my_unibo.ui.util

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mitch.my_unibo.datastore.SettingsDataStore
import java.util.*
import kotlinx.coroutines.runBlocking

sealed class AppLanguage(val languageTag: String) {
    object Italian : AppLanguage("it")
    object English : AppLanguage("en")
    object Spanish : AppLanguage("es")

    companion object {

        fun setLanguage(context: Context, appLanguage: AppLanguage) {
            val locale = Locale(appLanguage.languageTag)
            val resources = context.resources
            val config = resources.configuration

            context.createConfigurationContext(config)
            Locale.setDefault(locale)
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
        }

        fun setLanguageAndUpdate(context: Context, appLanguage: AppLanguage) {
            val locale = Locale(appLanguage.languageTag)
            val resources = context.resources
            val config = resources.configuration

            context.createConfigurationContext(config)
            Locale.setDefault(locale)
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)

            // save language to datastore
            runBlocking {
                SettingsDataStore(context).saveLanguage(appLanguage)
            }

            if (context is Activity) {
                context.recreate()
            }
        }

        fun supported(): List<Locale> {
            return listOf(
                Locale(Italian.languageTag),
                Locale(English.languageTag),
                Locale(Spanish.languageTag)
            )
        }

        @Composable
        fun selected(): Locale {
            val context = LocalContext.current

            return runBlocking {
                val language = SettingsDataStore(context).getLanguage()
                Locale(language.languageTag)
            }
        }
    }
}
