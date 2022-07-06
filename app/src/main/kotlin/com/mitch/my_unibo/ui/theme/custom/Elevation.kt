package com.mitch.my_unibo.ui.theme.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.annotations.CustomTheming

@CustomTheming
data class Elevation(
    val default: Dp = 0.dp,
    val light: Dp = 5.dp,
    val medium: Dp = 10.dp,
    val strong: Dp = 15.dp
)

val LocalElevation = compositionLocalOf { Elevation() }

val elevation: Elevation
    @Composable
    @ReadOnlyComposable
    get() = LocalElevation.current
