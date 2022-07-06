package com.mitch.my_unibo.ui.exams.components.booked_exams

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.capitalize
import com.mitch.my_unibo.ui.custom.cards.ExpandableCard
import com.mitch.my_unibo.ui.exams.components.ExamsCategory
import com.mitch.my_unibo.ui.exams.components.StudentExamsViewModel
import com.mitch.my_unibo.ui.profile.components.grades.ExamRow
import com.mitch.my_unibo.ui.util.SecondaryScreen

@Composable
fun BookedExamsScreen(
    studentExamsViewModel: StudentExamsViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    val examsCategory = listOf(
        ExamsCategory.FirstYear,
        ExamsCategory.SecondYear,
        ExamsCategory.ThirdYear
    )

    SecondaryScreen(
        title = stringResource(id = R.string.already_booked_exams).capitalize(),
        navController = navController,
        systemController = systemController
    ) {
        Column {
            examsCategory.forEach { category ->
                val bookedExams = studentExamsViewModel.bookedExamsPerYear(category.year)
                ExpandableCard(title = category.name) {
                    LazyColumn {
                        items(bookedExams) { exam ->
                            ExamRow(
                                viewModel = studentExamsViewModel,
                                exam = exam
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}
