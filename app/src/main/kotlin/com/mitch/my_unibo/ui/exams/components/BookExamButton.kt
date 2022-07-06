package com.mitch.my_unibo.ui.exams.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.onMultipleLines
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton

@Composable
fun BookExamButton(
    onClick: () -> Unit
) {
    PrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(150.dp)
    ) {
        Text(
            text = stringResource(R.string.book_exam).onMultipleLines(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
