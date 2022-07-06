package com.mitch.my_unibo.ui.courses.components.virtuale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.ui.util.SecondaryScreen

@Composable
fun VirtualeCourseScreen(
    virtualeViewModel: VirtualeViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController,
    idCourse: Int?
) {
    if (idCourse == null) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "couldn't find course",
                color = MaterialTheme.colors.onBackground
            )
        }
    } else {
        virtualeViewModel.findCourseById(idCourse)?.let { course ->
            SecondaryScreen(
                title = course.name,
                navController = navController,
                systemController = systemController
            ) {
                Column {
                    Text(text = "course with id = ${course.rating}")
                }
            }
        }
    }
}
