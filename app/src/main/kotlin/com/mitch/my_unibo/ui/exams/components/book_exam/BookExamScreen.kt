package com.mitch.my_unibo.ui.exams.components.book_exam

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.toOrdinal
import com.mitch.my_unibo.features.exam.Exam
import com.mitch.my_unibo.features.exam.student.StudentExam
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton
import com.mitch.my_unibo.ui.exams.components.ExamsViewModel
import com.mitch.my_unibo.ui.exams.components.StudentExamsViewModel
import com.mitch.my_unibo.ui.profile.components.StudentViewModel
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.util.AppLanguage
import com.mitch.my_unibo.ui.util.SecondaryScreen
import com.mitch.my_unibo.ui.util.SnackbarController
import java.time.format.TextStyle
import kotlinx.coroutines.launch

@Composable
fun BookExamScreen(
    examsViewModel: ExamsViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    examsIds: List<Int>
) {

    SecondaryScreen(
        title = stringResource(id = R.string.book_exam),
        navController = navController,
        systemController = systemController
    ) {

        Column {
            LazyColumn {
                items(examsIds) { examId ->
                    val exam = examsViewModel.findById(examId)
                    if (exam != null) {
                        BookableExamRow(
                            exam = exam,
                            scaffoldState = scaffoldState,
                            snackbarController = snackbarController
                        )

                        if (examId != examsIds.last()) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookableExamRow(
    exam: Exam,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    studentExamsViewModel: StudentExamsViewModel = hiltViewModel(),
    studentViewModel: StudentViewModel = hiltViewModel()
) {
    var clicksCount by remember { mutableStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(padding.medium),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${exam.date.dayOfMonth}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = exam.date.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        AppLanguage.selected()
                    ),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                modifier = Modifier.weight(2.5f)
            ) {
                Text(
                    text = exam.course.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${exam.course.yearOfTeaching.toOrdinal()} ${stringResource(id = R.string.year)}",
                    fontSize = 16.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "CFU:",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "${exam.course.cfu}",
                        fontSize = 16.sp
                    )
                }
            }
            PrimaryButton(
                onClick = {
                    clicksCount++
                    if (clicksCount % 2 == 0) {
                        studentExamsViewModel.removeExam(
                            StudentExam(
                                idStudent = studentViewModel.getLoggedStudent()!!.idStudent!!,
                                idExam = exam.idExam!!,
                                grade = 0,
                                date = exam.date,
                                booked = true
                            )
                        )
                        snackbarController.scope.launch {
                            snackbarController.showSnackbar(
                                scaffoldState = scaffoldState,
                                message = "Annullamento prenotazione"
                            )
                        }
                    } else {
                        studentExamsViewModel.addExam(
                            StudentExam(
                                idStudent = studentViewModel.getLoggedStudent()!!.idStudent!!,
                                idExam = exam.idExam!!,
                                grade = 0,
                                date = exam.date,
                                booked = true
                            )
                        )
                        snackbarController.scope.launch {
                            snackbarController.showSnackbar(
                                scaffoldState = scaffoldState,
                                message = "Esame Prenotato!"
                            )
                        }
                    }
                },
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth()
                    .height(50.dp)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    )
            ) {
                if (clicksCount % 2 == 0) {
                    Text(text = "PRENOTA")
                } else {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "book exam",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
