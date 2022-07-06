package com.mitch.my_unibo.ui.profile.components.grades

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.capitalize
import com.mitch.my_unibo.features.exam.student.StudentExam
import com.mitch.my_unibo.ui.custom.cards.ExpandableCard
import com.mitch.my_unibo.ui.exams.components.ExamsCategory
import com.mitch.my_unibo.ui.exams.components.StudentExamsViewModel
import com.mitch.my_unibo.ui.exams.components.taken_exams.TabItem
import com.mitch.my_unibo.ui.profile.components.StudentViewModel
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.util.SecondaryScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun GradesScreen(
    studentExamsViewModel: StudentExamsViewModel = hiltViewModel(),
    studentViewModel: StudentViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    var selectedTabIndex by remember { mutableStateOf(1) }
    val tabs = listOf(
        TabItem.AllExams,
        TabItem.TakenExams,
        TabItem.ExamsToTake
    )
    var selectedTabItem by remember { mutableStateOf(tabs[1]) }

    SecondaryScreen(
        title = stringResource(id = R.string.exams_taken).capitalize(),
        navController = navController,
        systemController = systemController
    ) {
        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.height(60.dp),
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = UniBoColor,
                indicator = { tabs ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabs[selectedTabIndex])
                            .height(4.dp)
                            .padding(horizontal = 30.dp)
                            .background(color = UniBoColor)
                    )
                }
            ) {
                tabs.forEachIndexed { tabIndex, tab ->
                    val isSelected = selectedTabIndex == tabIndex
                    Tab(
                        text = {
                            Text(
                                text = tab.name,
                                color = MaterialTheme.colors.onSurface,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            selectedTabIndex = tabIndex
                            // show content from selected tab number
                            selectedTabItem = tab
                        }
                    )
                }
            }

            val examsCategory = listOf(
                ExamsCategory.FirstYear,
                ExamsCategory.SecondYear,
                ExamsCategory.ThirdYear
            )

            examsCategory.forEach { category ->
                ExpandableCard(title = category.name) {
                    val allExams = studentExamsViewModel.examsInYear(category.year)
                    val takenExams = studentExamsViewModel.takenExamsPerYear(category.year)
                    val examsToTake = studentExamsViewModel.examsToTakePerYear(category.year)

                    val examsToConsider = when (selectedTabItem) {
                        TabItem.AllExams -> allExams
                        TabItem.TakenExams -> takenExams
                        TabItem.ExamsToTake -> examsToTake
                            .distinctBy { exam -> exam.course.name }
                            .map { exam ->
                                StudentExam(
                                    idStudent = studentViewModel.getLoggedStudent()!!.idStudent!!,
                                    idExam = exam.idExam!!,
                                    grade = 0,
                                    date = LocalDate.now(),
                                    booked = true
                                )
                            }
                    }

                    LazyColumn {
                        items(examsToConsider) { exam ->
                            ExamRow(
                                viewModel = studentExamsViewModel,
                                exam = exam
                            )
                            if (exam != examsToConsider.last()) {
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExamRow(
    viewModel: StudentExamsViewModel,
    exam: StudentExam
) {
    val examName = viewModel.examNameById(exam.idExam)
    val dayMonthYearDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Log.d("exams", exam.toString())

        Row(
            modifier = Modifier.weight(3f)
        ) {
            if (exam.grade > 0) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = examName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                        Text(text = exam.date.format(dayMonthYearDateFormatter))
                    }
                }
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "${exam.grade}",
                            modifier = Modifier.alignByBaseline(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "/",
                            modifier = Modifier.alignByBaseline()
                        )
                        Text(
                            text = "30L",
                            modifier = Modifier.alignByBaseline()
                        )
                    }
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = examName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (exam.date != LocalDate.now()) {
                        Text(text = exam.date.format(dayMonthYearDateFormatter))
                    }
                }
            }
        }
    }
}
