package com.mitch.my_unibo.annotations

/**
 * App themes must be annotated with @[Theme], in order to quickly identify a theme.
 * Since its name is not always descriptive, a theme type variable must be added.
 * Themes can only be:
 * - Light: light-tone background with accent colors for content
 * - Dark: dark-tone background with dark or non-dark colors for content
 *
 * @property name the name of the theme
 * @property type the type of the theme
 */
annotation class Theme(
    val name: String,
    val type: Type
) {
    enum class Type {
        LIGHT,
        DARK
    }
}
