package com.mitch.my_unibo.ui.theme.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.annotations.CustomTheming

@CustomTheming
data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 5.dp,
    val small: Dp = 10.dp,
    val medium: Dp = 20.dp,
    val large: Dp = 30.dp,
    val extraLarge: Dp = 50.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
