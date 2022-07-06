package com.mitch.my_unibo.ui.custom.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground
) = Text(
    text = text,
    modifier = modifier,
    color = color,
    fontSize = 25.sp,
    fontWeight = FontWeight.Bold
)
