package com.mitch.my_unibo.ui.courses.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitch.my_unibo.ui.util.AppLanguage
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters

@Composable
fun ThisWeek(
    today: LocalDate?,
    daysThisWeek: List<LocalDate>,
    selectedOption: LocalDate?,
    onOptionSelected: (LocalDate) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        daysThisWeek.forEach { day ->
            val selected = (day == selectedOption)
            val daysTextColors = when {
                selected -> Color.White
                day < today -> Color.Gray
                else -> Color.Black
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .selectable(
                        selected = selected,
                        onClick = {
                            onOptionSelected(day)
                            onClick()
                        },
                        role = Role.RadioButton
                    ),
                elevation = if (selected) 10.dp else 0.dp,
                backgroundColor = if (selected) MaterialTheme.colors.primary else Color(0xFFEEEEEE)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = day.dayOfMonth.toString(),
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 20.sp,
                        color = daysTextColors
                    )
                    Text(
                        text = day.dayOfWeek.getDisplayName(
                            TextStyle.SHORT,
                            AppLanguage.selected()
                        ),
                        color = daysTextColors
                    )
                }
            }
        }
    }
}

@Composable
private fun daysOfThisWeek(today: LocalDate): List<LocalDate> {
    val monday = if (today.dayOfWeek in listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)) {
        today.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
    } else {
        today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    }


    /**
     * From monday -> 0
     * to friday -> 4
     */
    val mondayToFriday = 0..4L
    return mondayToFriday.map { monday.plusDays(it) }.toList()
}
