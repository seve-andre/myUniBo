package com.mitch.my_unibo.ui.courses.components.virtuale

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.toOrdinal
import com.mitch.my_unibo.features.course.Course
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.util.SecondaryScreen
import com.mitch.my_unibo.ui.util.SnackbarController
import kotlinx.coroutines.launch

@Composable
fun SearchCourseScreen(
    virtualeViewModel: VirtualeViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController
) {
    var courses by remember { mutableStateOf(virtualeViewModel.findCoursesToAdd()) }
    var searchCourseText by rememberSaveable { mutableStateOf("") }

    SecondaryScreen(
        title = stringResource(R.string.search_course),
        navController = navController,
        systemController = systemController
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            OutlinedTextField(
                value = searchCourseText,
                onValueChange = {
                    searchCourseText = it
                    courses = virtualeViewModel.findCoursesLike(searchCourseText)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding.medium),
                label = {
                    Text(text = stringResource(R.string.search_course))
                },
                placeholder = {
                    Text(text = stringResource(R.string.search_course))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                shape = MaterialTheme.shapes.medium
            )
            if (courses.isNotEmpty()) {
                LazyColumn {
                    items(courses) { course ->
                        CoursesToAddRow(
                            virtualeViewModel = virtualeViewModel,
                            course = course,
                            scaffoldState = scaffoldState,
                            snackbarController = snackbarController
                        )
                        if (course != courses.last()) {
                            Divider()
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "No course found")
                }
            }
        }
    }
}

@Composable
fun CoursesToAddRow(
    virtualeViewModel: VirtualeViewModel,
    course: Course,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController
) {
    var clicksCount by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1.3f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = course.name,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${course.yearOfTeaching.toOrdinal()} ${stringResource(id = R.string.year)}",
                    color = MaterialTheme.colors.onSurface,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "CFU:",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "${course.cfu}",
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
            PrimaryButton(
                onClick = {
                    clicksCount++
                    if (clicksCount % 2 == 0) {
                        virtualeViewModel.removeCourseToStudent(course.idCourse!!)
                    } else {
                        virtualeViewModel.enrollStudentToCourse(course.idCourse!!)
                        snackbarController.scope.launch {
                            snackbarController.showSnackbar(
                                scaffoldState = scaffoldState,
                                message = "Course added!"
                            )
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
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
                    Text(text = "AGGIUNGI")
                } else {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "add course",
                        tint = Color.White
                    )
                }
            }
        }
    }

}
