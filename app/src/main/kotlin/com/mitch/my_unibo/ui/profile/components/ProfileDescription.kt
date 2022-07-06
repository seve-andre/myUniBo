package com.mitch.my_unibo.ui.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.R
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.ui.util.SnackbarController
import kotlinx.coroutines.launch

@Composable
fun ProfileDescription(
    student: Student,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController
) {
    // used to copy text value when tapping on either email or student ID
    val clipboardManager = LocalClipboardManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        StudentName(name = "${student.firstName} ${student.lastName}")
        EmailDescription(
            email = student.email,
            scaffoldState = scaffoldState,
            snackbarController = snackbarController,
            clipboardManager = clipboardManager
        )
        StudentIdDescription(
            idStudentFormatted = StudentIdFormatter.formatId(student.idStudent!!),
            scaffoldState = scaffoldState,
            snackbarController = snackbarController,
            clipboardManager = clipboardManager
        )
    }
}

@Composable
fun StudentName(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun EmailDescription(
    email: String,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    clipboardManager: ClipboardManager
) {
    Row(
        modifier = Modifier.clickable(
            onClick = {
                clipboardManager.setText(AnnotatedString(email))
                snackbarController.scope.launch {
                    snackbarController.showSnackbar(
                        scaffoldState = scaffoldState,
                        message = "Email copied!"
                    )
                }
            }
        ),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(text = "Email:", fontWeight = FontWeight.Bold)
        Text(text = email)
    }
}

@Composable
private fun StudentIdDescription(
    idStudentFormatted: String,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    clipboardManager: ClipboardManager
) {
    Row(
        modifier = Modifier.clickable(
            onClick = {
                clipboardManager.setText(AnnotatedString(idStudentFormatted))
                snackbarController.scope.launch {
                    snackbarController.showSnackbar(
                        scaffoldState = scaffoldState,
                        message = "Student ID copied!"
                    )
                }
            }
        ),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(text = "${stringResource(R.string.id_student)}:", fontWeight = FontWeight.Bold)
        Text(text = idStudentFormatted)
    }
}

@Preview
@Composable
fun ProfileDescriptionPreview() {
//    ProfileDescription()
}
