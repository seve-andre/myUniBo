package com.mitch.my_unibo.ui.exams.components.book_exam

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton
import com.mitch.my_unibo.ui.custom.cards.ExpandableCard
import com.mitch.my_unibo.ui.exams.components.ExamsCategory
import com.mitch.my_unibo.ui.exams.components.StudentExamsViewModel
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.navigation.parcelables.BookExamsParcelable
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.util.SecondaryScreen

@Composable
fun ChooseExamsToBookScreen(
    viewModel: StudentExamsViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    val examsCategory = listOf(
        ExamsCategory.FirstYear,
        ExamsCategory.SecondYear,
        ExamsCategory.ThirdYear
    )

    val chosenExams = mutableListOf<Int>()

    SecondaryScreen(
        title = stringResource(R.string.choose_exams),
        navController = navController,
        systemController = systemController
    ) {
        Column {
            examsCategory.forEach { category ->
                val examsToBook = viewModel.bookableExamsPerYear(category.year)

                ExpandableCard(title = category.name) {
                    LazyColumn {
                        items(examsToBook) { exam ->
                            val examName = viewModel.examNameById(exam.idExam!!)
                            var checkboxState by remember { mutableStateOf(false) }

                            Row(
                                modifier = Modifier.background(MaterialTheme.colors.surface),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = checkboxState,
                                    onCheckedChange = {
                                        checkboxState = !checkboxState
                                        if (checkboxState) {
                                            chosenExams.add(exam.idExam)
                                            Log.d("exams", chosenExams.toString())
                                        } else {
                                            chosenExams.remove(exam.idExam)
                                        }
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = UniBoColor,
                                        checkmarkColor = Color.White
                                    )
                                )
                                Text(
                                    text = examName,
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(padding.medium)
            ) {
                PrimaryButton(
                    onClick = {
                        if (chosenExams.isNotEmpty()) {
                            val bookExamsParcelable = BookExamsParcelable(chosenExams)
                            navController.currentBackStackEntry?.arguments?.putParcelable(
                                "examsToBook",
                                bookExamsParcelable
                            )
                            navController.navigate(Screen.BookExam.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "AVANZA")
                }
            }
        }
    }
}
