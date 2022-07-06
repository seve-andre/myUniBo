package com.mitch.my_unibo.extensions

/**
 * Divides a sentence into single words, so that every word is displayed in a different line
 * to the others.
 */
fun String.onMultipleLines(): String {
    return this.replace(" ", "\n")
}

fun String.capitalize(): String {
    return this.replaceFirstChar { it.uppercase() }
}
