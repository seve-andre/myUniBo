package com.mitch.my_unibo.extensions

import kotlin.math.roundToInt

/**
 * Converts [Float] number to percentage [String].
 * Rounds up number to [Int], following the rounding-up-to-the-nearest-integer convention.
 *
 * Examples:
 * - 0.33f -> "33%"
 * - 0.655f -> "66%"
 * - 0.223f -> "22%"
 *
 * @return number converted to percentage string
 */
fun Float.toPercentage(): String {
    return "${(this * 100).roundToInt()}%"
}
