package com.mitch.my_unibo.ui.theme

import android.app.Activity
import android.content.Context
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.mitch.my_unibo.R
import com.mitch.my_unibo.annotations.Theme
import com.mitch.my_unibo.datastore.SettingsDataStore
import kotlinx.coroutines.runBlocking

sealed class AppTheme(val colors: Colors, val themeType: Theme.Type) {
    object Light : AppTheme(LightColorPalette, Theme.Type.LIGHT)
    object Dark : AppTheme(DarkColorPalette, Theme.Type.DARK)

    @Composable
    fun name(): String {
        return when (this) {
            Light -> stringResource(id = R.string.light_theme)
            Dark -> stringResource(id = R.string.dark_theme)
        }
    }

    companion object {

        fun themeFromId(themeId: Int): AppTheme {
            return when (themeId) {
                0 -> Light
                1 -> Dark

                // default theme
                else -> Light
            }
        }

        private fun idFromTheme(appTheme: AppTheme): Int {
            return when (appTheme) {
                Light -> 0
                Dark -> 1
            }
        }

        fun setThemeAndUpdate(context: Context, appTheme: AppTheme) {
            // save language to datastore
            runBlocking {
                SettingsDataStore(context).saveTheme(idFromTheme(appTheme))
            }

            if (context is Activity) {
                context.recreate()
            }
        }

        fun supported(): List<AppTheme> {
            return listOf(
                Light,
                Dark
            )
        }

        @Composable
        fun selected(): AppTheme {
            val context = LocalContext.current

            return runBlocking {
                val themeId = SettingsDataStore(context).getThemeId()
                themeFromId(themeId)
            }
        }
    }
}

@Theme(name = "Dark", type = Theme.Type.DARK)
private val DarkColorPalette = darkColors(
    primary = UniBoColor,
    background = Color(0xFF000000),
    onBackground = Color(0xFFFAFAFA),
    secondary = Color(0xFF2F7FEA),
    secondaryVariant = Color.LightGray,
//    surface = Color(0xFF1F1F1F),
    surface = Color(0xFF2E2E2E),
    onSurface = Color(0xFFFAFAFA)
)

@Theme(name = "Light", type = Theme.Type.LIGHT)
private val LightColorPalette = lightColors(
    primary = UniBoColor,
    background = Color(0xFFF4F4F6),
    onBackground = Color(0xFF2E2F31),
    secondary = Color(0xFF2F7FEA),
    secondaryVariant = Color(0xFFEEEEEE),
    surface = Color.White,
    onSurface = Color(0xFF2E2F31)
)

@Composable
fun MyUniBoTheme(
    theme: AppTheme = AppTheme.Light,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = theme.colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun isThemeDark(): Boolean {
    val context = LocalContext.current
    val themeId = runBlocking {
        SettingsDataStore(context)
            .getThemeId()
    }

    return AppTheme.themeFromId(themeId).themeType == Theme.Type.DARK
}
