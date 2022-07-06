package com.mitch.my_unibo.extensions

import kotlin.math.abs
import kotlin.math.log10

fun Int.length() = when (this) {
    0 -> 1
    else -> log10(abs(this.toDouble())).toInt() + 1
}
