package com.mitch.my_unibo.ui.exams.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.onMultipleLines

@Composable
fun AlreadyBookedExamsButton(
    studentExamsViewModel: StudentExamsViewModel,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            // fill remaining space from 1f (entire row) - 0.6f (BookExamButton)
            .fillMaxWidth()
            .height(150.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${
                    studentExamsViewModel.findStudentExams()
                        .filter { exam -> exam.grade == 0 }.size
                }",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.already_booked_exams).onMultipleLines(),
                textAlign = TextAlign.Center
            )
        }
    }
}
