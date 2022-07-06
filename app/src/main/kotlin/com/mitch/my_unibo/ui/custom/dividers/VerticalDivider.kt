package com.mitch.my_unibo.ui.custom.dividers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider(
    height: Dp
) {
    Box(
        modifier = Modifier
            .height(height)
            .width(1.dp)
            .background(MaterialTheme.colors.onSurface.copy(0.3f))
    )
}
