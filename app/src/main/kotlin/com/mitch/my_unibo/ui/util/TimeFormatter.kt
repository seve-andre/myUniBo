package com.mitch.my_unibo.ui.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * [TimeFormatter] formats hour-minute time into the format HH:mm.
 * Uses [LocalTime][java.time.LocalTime] formatter based on given pattern.
 *
 * Example usage:
 * ```
 * - TimeFormatter.format(LocalTime.of(11, 10)) -> "11:10"
 * - TimeFormatter.format(LocalTime.of(11, 0)) -> "11:00"
 * ```
 */
class TimeFormatter {

    companion object {
        fun format(pattern: String, time: LocalTime): String {
            return time.format(DateTimeFormatter.ofPattern(pattern))
        }
    }
}
