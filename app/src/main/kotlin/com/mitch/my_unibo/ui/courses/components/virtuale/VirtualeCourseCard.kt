package com.mitch.my_unibo.ui.courses.components.virtuale

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.toOrdinal
import com.mitch.my_unibo.features.course.Course
import com.mitch.my_unibo.features.professor.Professor
import com.mitch.my_unibo.ui.custom.dialogs.InfoDialog
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.theme.isThemeDark
import java.time.format.DateTimeFormatter

@Composable
fun VirtualeCourseCard(
    course: Course,
    navController: NavHostController
) {
    var showCourseDetails by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        // show course details
                        showCourseDetails = true
                    },
                    onTap = {
                        navController.navigate("${Screen.SeeCourse.route}/${course.idCourse}")
                    }
                )
            }
    ) {
        Column {
            // lesson (contains **course** and **professors** references)
            Row(
                modifier = Modifier
                    .background(
                        if (isThemeDark()) {
                            Color.DarkGray
                        } else {
                            Color(0xFFEEEEEE)
                        }
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = course.name,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        // example 3rd year - 3° anno
                        text = "${course.yearOfTeaching.toOrdinal()} ${stringResource(R.string.year)}",
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 10.dp,
                                bottomStart = 10.dp
                            )
                        )
                        .background(Color(0xFFD6E2EF))
                        .padding(10.dp)
                ) {
                    val yearLastTwoDigitsFormatter = DateTimeFormatter.ofPattern("yy")
                    val startYear = course.academicYear.startYear.format(yearLastTwoDigitsFormatter)
                    val endYear = course.academicYear.endYear.format(yearLastTwoDigitsFormatter)

                    Text(
                        text = """${startYear}/${endYear}""",
                        color = Color.Black
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.surface),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                ) {
                    Row {
                        Text(
                            text = "Prof:",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = course.professors
                                .filter { p -> p.type == Professor.Type.ASSOCIATE }
                                .joinToString { p ->
                                    "${p.firstName} ${p.lastName}"
                                },
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    Row {
                        Text(
                            text = "Tutor:",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = course.professors
                                .filter { p -> p.type == Professor.Type.TUTOR }
                                .joinToString { p ->
                                    "${p.firstName} ${p.lastName}"
                                },
                            color = MaterialTheme.colors.onSurface,
                        )
                    }
                }
                /*TextButton(
                    modifier = Modifier
                        .padding(20.dp),
                    onClick = {
                        // show course details
                        showCourseDetails = true
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF2F7FEA),
                        backgroundColor = MaterialTheme.colors.surface
                    )
                ) {
                    Text(text = stringResource(R.string.details))
                }*/
            }
        }
    }
}

@Preview
@Composable
fun VirtualeCourseCardPreview() {

}
