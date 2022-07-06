package com.mitch.my_unibo.ui.exams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.ui.exams.components.AlreadyBookedExamsButton
import com.mitch.my_unibo.ui.exams.components.BookExamButton
import com.mitch.my_unibo.ui.exams.components.StatsCard
import com.mitch.my_unibo.ui.exams.components.StudentExamsViewModel
import com.mitch.my_unibo.ui.exams.components.TakenExamsButton
import com.mitch.my_unibo.ui.exams.components.fake_stats.FakeStatsButton
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.theme.isThemeDark

@Composable
fun ExamsScreen(
    studentExamsViewModel: StudentExamsViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    systemController.setStatusBarColor(
        MaterialTheme.colors.surface,
        darkIcons = !isThemeDark()
    )

    systemController.setNavigationBarColor(
        color = UniBoColor,
        darkIcons = false
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = padding.medium)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(
                top = padding.medium,
                bottom = padding.large
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                BookExamButton {
                    navController.navigate(Screen.ChooseExamsToBook.route)
                }
                AlreadyBookedExamsButton(studentExamsViewModel) {
                    navController.navigate(Screen.SeeUpcomingExams.route)
                }
            }

            TakenExamsButton(studentExamsViewModel) {
                navController.navigate(Screen.SeeTakenExams.route)
            }

            StatsCard(studentExamsViewModel)
            FakeStatsButton {
                navController.navigate(Screen.FakeStats.route)
            }

//            FakeStatsScreen(studentExamsViewModel)
        }
    }
}




@Preview
@Composable
fun ExamsScreenPreview() {
//    ExamsScreen()
}
