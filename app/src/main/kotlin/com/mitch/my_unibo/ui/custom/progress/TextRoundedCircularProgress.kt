package com.mitch.my_unibo.ui.custom.progress

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitch.my_unibo.extensions.toPercentage

@Composable
fun TextRoundedCircularProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Dp,
    text: String
) {
    // value gotta be adjusted due to graphical issue
    val adjustedProgress = remember { mutableStateOf(progress) }

    if (progress in (0.91f..0.99f)) {
        adjustedProgress.value = 0.90f
    }

    Box(
        modifier = modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        // circle progress
        CircularProgressIndicator(
            progress = 1f,
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFEBEBEB),
            strokeWidth = strokeWidth
        )
        if (progress != 0f) {
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
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = progress.toPercentage(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = text)
        }
    }
}

@Preview
@Composable
fun TextRoundedCircularProgressPreview() {
    val color = Color.Red
    val strokeWidth = 15.dp

    Column {
        TextRoundedCircularProgress(
            progress = 0f,
            color = color,
            strokeWidth = strokeWidth,
            text = "Ciao"
        )
        TextRoundedCircularProgress(
            progress = 0.655f,
            color = color,
            strokeWidth = strokeWidth,
            text = ""
        )
        TextRoundedCircularProgress(
            progress = 0.94f,
            color = color,
            strokeWidth = strokeWidth,
            text = ""
        )
        TextRoundedCircularProgress(
            progress = 0.99f,
            color = color,
            strokeWidth = strokeWidth,
            text = ""
        )
        TextRoundedCircularProgress(
            progress = 1f,
            color = color,
            strokeWidth = strokeWidth,
            text = ""
        )
    }
}
