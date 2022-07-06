package com.mitch.my_unibo.ui.profile.components.badge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitch.my_unibo.R
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.ui.profile.components.ProfileImage
import com.mitch.my_unibo.ui.profile.components.StudentIdFormatter
import com.mitch.my_unibo.ui.profile.components.StudentName
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.theme.custom.padding

@Composable
fun BadgeCard(
    student: Student
) {
    Card(
        modifier = Modifier.height(250.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding.small)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.uni_logo),
                        contentDescription = "logo unibo"
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(3f)
                        .padding(bottom = padding.small)
                ) {
                    Column {
                        Text(
                            text = "ALMA MATER STUDIORUM",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = padding.medium),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.End
                        )
                        Text(
                            text = stringResource(R.string.university_of_bologna),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = padding.medium),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
            Divider(color = UniBoColor.copy(0.6f))
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProfileImage(
                        idProfileImage = R.drawable.ic_user_outlined_white,
                        contentDescription = "student picture",
                        modifier = Modifier
                            .padding(
                                start = padding.small,
                                top = padding.small,
                                end = padding.small,
                                bottom = padding.small
                            )
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(3f)
                        .padding(
                            top = padding.small,
                            end = padding.small
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        StudentName(name = "${student.firstName} ${student.lastName}")
                        Email(student.email)
                        StudentID(student.idStudent!!)
                    }
                }
            }
        }
    }
}

@Composable
private fun StudentID(idStudent: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = "${stringResource(R.string.id_student)}:",
            fontWeight = FontWeight.Bold
        )
        Text(text = StudentIdFormatter.formatId(idStudent))
    }
}

@Composable
private fun Email(studentEmail: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(text = "Email:", fontWeight = FontWeight.Bold)
        Text(text = studentEmail)
    }
}

@Preview
@Composable
fun BadgeCardPreview() {
    BadgeCard(
        student = Student(
            1,
            "Andrea",
            "Severi",
            "andrea.severi12@studio.unibo.it",
            ""
        )
    )
}
