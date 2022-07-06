package com.mitch.my_unibo.ui.courses.components.virtuale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.ui.custom.text.Title
import com.mitch.my_unibo.ui.navigation.Screen

@Composable
fun VirtualeCourses(
    viewModel: VirtualeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Title(text = stringResource(R.string.my_courses))
            SearchCourseButton {
                navController.navigate(Screen.SearchCourse.route)
            }
        }

        val courses = viewModel.findStudentCourses()

        if (courses.isEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Non sei iscritto a nessuno corso")
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                courses.forEach {
                    VirtualeCourseCard(
                        course = it,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchCourseButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color(0xFF2F7FEA)
        )
    ) {
        Text(text = stringResource(R.string.add_course))
    }
}

@Preview
@Composable
fun VirtualeCoursesPreview() {
//    VirtualeCourses()
}
