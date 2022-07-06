package com.mitch.my_unibo.extensions

import android.icu.text.MessageFormat
import android.os.Build
import androidx.compose.runtime.Composable
import com.mitch.my_unibo.ui.util.AppLanguage
import java.util.*

/**
 * Converts an [Int] number into an ordinal number ([String]) based on system language.
 * Uses [Locale] to detect user system language preference.
 *
 * Examples:
 * - 1 -> English format: 1st
 * - 11 -> English format: 11th
 * - 2 -> Italian format: 2°
 *
 * @return ordinal number based on system language
 */
@Composable
fun Int.toOrdinal(): String {
    val number = this
    val locale = AppLanguage.selected()

    // N version: android 7 Nougat

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        if (locale.language == "en") {
            /**
             * Exceptions:
             *  - 11th
             *  - 12th
             *  - 13th
             */
            if (number in 11..13) {
                return "${number}th"
            }

            return when (number % 10) {
                1 -> "${number}st"
                2 -> "${number}nd"
                3 -> "${number}rd"
                else -> "${number}th"
            }
        } else {
            return "${number}°"
        }
    } else {
        // @RequiresApi(Build.VERSION_CODES.N)
        val formatter = MessageFormat("{0,ordinal}", locale)
        return formatter.format(arrayOf(number))
    }
}
