package com.mitch.my_unibo.ui.exams.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun TakenExamsButton(
    studentExamsViewModel: StudentExamsViewModel,
    onClick: () -> Unit
) {
    val statsCalculator = studentExamsViewModel.statsCalculator

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "${statsCalculator.nTakenExams()}",
                    modifier = Modifier.alignByBaseline(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.out_of),
                    modifier = Modifier.alignByBaseline(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "${statsCalculator.nTotalExams()}",
                    modifier = Modifier.alignByBaseline(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = stringResource(R.string.exams_taken),
                textAlign = TextAlign.Center
            )
        }
    }
}
