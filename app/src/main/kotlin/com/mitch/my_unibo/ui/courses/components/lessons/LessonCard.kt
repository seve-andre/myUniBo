package com.mitch.my_unibo.ui.courses.components.lessons

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.common.data.KalendarEvent
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import com.mitch.my_unibo.R
import com.mitch.my_unibo.features.lesson.Lesson
import com.mitch.my_unibo.features.professor.Professor
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.util.AppLanguage
import com.mitch.my_unibo.ui.util.TimeFormatter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters

@Composable
fun LessonsOfTheDay(
    lessonViewModel: LessonViewModel = hiltViewModel()
) {
    val today = LocalDate.now()
    var lessonsOfTheDay by remember { mutableStateOf(lessonsOfDay(lessonViewModel, today)) }

    // layout should "make space" for the calendar, so the user can select a specific day
    var showCalendar by remember { mutableStateOf(false) }

    val idArrowDown = R.drawable.ic_down_arrow
    val idArrowUp = R.drawable.ic_up_arrow

    val dayMonthYearDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    val todayFormatted = today.format(dayMonthYearDateFormatter)

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TodayText(todayFormatted)

        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val daysThisWeek = daysOfThisWeek(today)
            var selectedDay by remember { mutableStateOf(today) }

            if (showCalendar) {
                Calendar { calendarSelectedDay, _ ->
                    selectedDay = calendarSelectedDay
                    lessonsOfTheDay = lessonsOfDay(lessonViewModel, calendarSelectedDay)
                }
                Spacer(modifier = Modifier.height(30.dp))
            } else {
                ThisWeek(
                    today = today,
                    daysThisWeek = daysThisWeek,
                    selectedDay = selectedDay
                ) { buttonSelectedDay ->
                    selectedDay = buttonSelectedDay
                    lessonsOfTheDay = lessonsOfDay(lessonViewModel, buttonSelectedDay)

                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            IconButton(
                onClick = {
                    showCalendar = !showCalendar
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (!showCalendar) idArrowDown else idArrowUp
                    ),
                    contentDescription = if (!showCalendar) "open calendar for more dates" else "hide calendar"
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (lessonsOfTheDay.isEmpty()) {
                Text(text = stringResource(R.string.no_lessons_message))
            } else {
                Text(
                    text = "${stringResource(id = R.string.lessons)} ${
                        selectedDay.format(
                            dayMonthYearDateFormatter
                        )
                    }",
                    modifier = Modifier.align(Alignment.Start),
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                ) {
                    lessonsOfTheDay.forEach { lesson ->
                        LessonRow(lesson)

                        val last = lesson == lessonsOfTheDay.last()
                        if (!last) {
                            Divider(color = Color.LightGray)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TodayText(todayFormatted: String?) {
    Text(
        text = "${stringResource(R.string.today)} $todayFormatted",
        modifier = Modifier.padding(start = 0.dp),
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Medium
    )
}

@Composable
private fun Calendar(
    onDayClick: (LocalDate, KalendarEvent?) -> Unit
) {
    Kalendar(
        kalendarType = KalendarType.Firey(),
        onCurrentDayClick = onDayClick,
        kalendarKonfig = KalendarKonfig(
            locale = AppLanguage.selected()
        ),
        kalendarStyle = KalendarStyle(
            kalendarBackgroundColor = Color.Transparent,
            kalendarColor = MaterialTheme.colors.surface,
            kalendarSelector = KalendarSelector.Circle(
                selectedColor = MaterialTheme.colors.primary,
                defaultColor = MaterialTheme.colors.surface,
                defaultTextColor = MaterialTheme.colors.onSurface,
                todayColor = Color.Transparent
            ),
            elevation = 0.dp
        )
    )
}

@Composable
private fun ThisWeek(
    today: LocalDate?,
    daysThisWeek: List<LocalDate>,
    selectedDay: LocalDate?,
    onDayClick: (LocalDate) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        daysThisWeek.forEach { day ->
            val selected = (day == selectedDay)
            val daysTextColors = when {
                selected -> Color.White
                day < today -> MaterialTheme.colors.onSurface.copy(0.4f)
                else -> MaterialTheme.colors.onSurface
            }

            ThisWeekDayCard(
                modifier = Modifier.weight(1f),
                isSelected = selected,
                day = day,
                color = daysTextColors,
                onDayClick = onDayClick
            )
        }
    }
}

@Composable
private fun ThisWeekDayCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    day: LocalDate,
    color: Color,
    onDayClick: (LocalDate) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = { onDayClick(day) },
                role = Role.RadioButton
            ),
        elevation = if (isSelected) 10.dp else 0.dp,
        backgroundColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = day.dayOfMonth.toString(),
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 20.sp,
                color = color
            )
            Text(
                text = day.dayOfWeek.getDisplayName(
                    TextStyle.SHORT,
                    AppLanguage.selected()
                ),
                color = color
            )
        }
    }
}


private fun lessonsOfDay(lessonViewModel: LessonViewModel, day: LocalDate): List<Lesson> {
    return lessonViewModel.findLessonsByDay(day)
}

@Composable
private fun LessonRow(lesson: Lesson) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = TimeFormatter.format("HH:mm", lesson.startTime),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = TimeFormatter.format("HH:mm", lesson.endTime),
                color = MaterialTheme.colors.onSurface
            )
        }
        Column(
            modifier = Modifier.padding(start = 15.dp)
        ) {
            Text(
                text = lesson.course.name,
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Aula:",
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = lesson.room,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Prof:",
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = lesson.professors()
                        .filter { professor -> professor.type == Professor.Type.ASSOCIATE }
                        .joinToString { professor -> "${professor.firstName} ${professor.lastName}" },
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
private fun DayOfThisWeekCard(
    day: LocalDate,
    selected: Boolean,
    onOptionSelected: (LocalDate) -> Unit,
    colors: Color
) {
    Card(
        modifier = Modifier
            .width(60.dp)
            .selectable(
                selected = selected,
                onClick = {
                    onOptionSelected(day)
                },
                role = Role.RadioButton
            ),
        elevation = if (selected) 10.dp else 0.dp,
        backgroundColor = if (selected) UniBoColor else Color(0xFFEEEEEE)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = day.dayOfMonth.toString(),
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 20.sp,
                color = colors
            )
            Text(
                text = day.dayOfWeek.getDisplayName(
                    TextStyle.SHORT,
                    AppLanguage.selected()
                ),
                color = colors
            )
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

@Preview
@Composable
fun LessonCardPreview() {
    LessonsOfTheDay()
}
