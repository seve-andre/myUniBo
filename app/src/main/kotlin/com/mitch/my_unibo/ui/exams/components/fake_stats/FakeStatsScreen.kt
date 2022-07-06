package com.mitch.my_unibo.ui.exams.components.fake_stats

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.features.exam.student.StudentExam
import com.mitch.my_unibo.ui.exams.components.ExamsViewModel
import com.mitch.my_unibo.ui.exams.components.StudentExamsViewModel
import com.mitch.my_unibo.ui.profile.components.StudentViewModel
import com.mitch.my_unibo.ui.util.SecondaryScreen
import java.time.LocalDate
import java.util.*

@Composable
fun FakeStatsScreen(
    studentExamsViewModel: StudentExamsViewModel = hiltViewModel(),
    studentViewModel: StudentViewModel = hiltViewModel(),
    examsViewModel: ExamsViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    var takenExams by remember { mutableStateOf(studentExamsViewModel.findStudentExams()) }
    var examsToTake by remember { mutableStateOf(studentExamsViewModel.examsToTake()) }
    val statsCalculator = studentExamsViewModel.statsCalculator
    var expanded by remember { mutableStateOf(false) }

    var newGpaFieldText by remember { mutableStateOf("${statsCalculator.gpa()}") }
    var newStartingGradeFieldText by remember { mutableStateOf("${statsCalculator.startingGrade()}") }

    var addedExams by remember { mutableStateOf(listOf<Int>()) }

    val textFieldInitValues = List(examsToTake.size) { "" }
    val gradesList = remember { mutableStateListOf<String>().apply { addAll(textFieldInitValues) } }

    SecondaryScreen(
        title = "Media ipotetica",
        navController = navController,
        systemController = systemController
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${statsCalculator.gpa()}",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = stringResource(R.string.gpa),
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = String.format(
                            // uses dot as decimal separator
                            locale = Locale.US,
                            // just 2 decimals
                            format = "%.2f",
                            statsCalculator.startingGrade()
                        ),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(
                        text = stringResource(R.string.starting_grade),
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = String.format(
                            // uses dot as decimal separator
                            locale = Locale.US,
                            // just 2 decimals
                            format = "%.2f",
                            newGpaFieldText.toFloat()
                        ),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "${stringResource(R.string.nuovo)} ${stringResource(R.string.gpa)}",
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = String.format(
                            // uses dot as decimal separator
                            locale = Locale.US,
                            // just 2 decimals
                            format = "%.2f",
                            newStartingGradeFieldText.toFloat()
                        ),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(
                        text = "${stringResource(R.string.nuovo)} ${stringResource(R.string.starting_grade)}",
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
            Row(/*
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically*/
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(2.5f)
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.TopStart)
                        ) {
                            OutlinedTextField(
                                value = "Esami",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    IconButton(onClick = { expanded = true }) {
                                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                                    }
                                },
                                maxLines = 1
                            )

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                examsToTake.forEach { exam ->
                                    DropdownMenuItem(
                                        onClick = {
                                            expanded = false
                                            addedExams = addedExams + exam.idExam!!
                                            examsToTake = examsToTake - exam
                                        }
                                    ) {
                                        Text(text = exam.course.name)
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.animateContentSize(
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        addedExams.forEachIndexed { index, idExam ->
                            val exam = examsViewModel.findById(idExam)
                            var showError by remember { mutableStateOf(false) }
                            val maxChar = 2

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = exam!!.course.name,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .weight(2.5f)
                                        .fillMaxWidth(),
                                    label = {
                                        Text(text = "Esame")
                                    },
                                    readOnly = true,
                                    maxLines = 1
                                )
                                OutlinedTextField(
                                    value = gradesList[index],
                                    onValueChange = {
                                        if (it.length <= maxChar) {
                                            gradesList[index] = it
                                        }
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    label = {
                                        Text(text = "voto")
                                    },
                                    placeholder = {
                                        Text(text = "voto")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    )
                                )
                                OutlinedTextField(
                                    value = "${exam.course.cfu}",
                                    onValueChange = {},
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    readOnly = true,
                                    label = {
                                        Text(text = "cfu")
                                    },
                                    placeholder = {
                                        Text(text = "cfu")
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Button(
                onClick = {
                    addedExams.forEachIndexed { index, idExam ->
                        takenExams = takenExams + StudentExam(
                            idStudent = studentViewModel.getLoggedStudent()!!.idStudent!!,
                            idExam = idExam,
                            grade = gradesList[index].toInt(),
                            date = LocalDate.now(),
                            booked = true
                        )
                    }

                    val gpa = statsCalculator.gpaFromExams(takenExams)
                    val startingGrade = (gpa * 11) / 3

                    newGpaFieldText = "$gpa"
                    newStartingGradeFieldText = "$startingGrade"

                    takenExams = studentExamsViewModel.findStudentExams()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "CALCOLA")
            }
        }
    }
}
