package com.mitch.my_unibo.ui.custom.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCircularProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Dp,
) {
    // value gotta be adjusted due to graphical issue
    val adjustedProgress = remember { mutableStateOf(progress) }

    if (progress in (0.95f..0.99f)) {
        adjustedProgress.value = 0.94f
    }

    Box(modifier = modifier.size(100.dp)) {
        // outer circle
        CircularProgressIndicator(
            progress = 1f,
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFEBEBEB),
            strokeWidth = strokeWidth
        )
        // inner circle
        CircularProgressIndicator(
            progress = adjustedProgress.value,
            modifier = Modifier.fillMaxSize(),
            color = color,
            strokeWidth = strokeWidth
        )
        // start circle
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.TopCenter)
                .size(strokeWidth)
                .background(color, CircleShape)
        )
        // end circle
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .rotate(360 * adjustedProgress.value)
                .wrapContentSize(align = Alignment.TopCenter)
                .size(strokeWidth)
                .background(color, CircleShape)
        )
    }
}
