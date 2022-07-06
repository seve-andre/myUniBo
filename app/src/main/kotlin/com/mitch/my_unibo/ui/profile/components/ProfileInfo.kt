package com.mitch.my_unibo.ui.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mitch.my_unibo.R
import com.mitch.my_unibo.ui.util.SnackbarController

@Composable
fun ProfileInfo(
    studentViewModel: StudentViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController
) {
    val student = studentViewModel.getLoggedStudent()

    Row(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(
            idProfileImage = R.drawable.ic_user_outlined_white,
            contentDescription = "Profile image of ${student?.firstName} ${student?.lastName}"
        )
        ProfileDescription(
            student = student!!,
            scaffoldState = scaffoldState,
            snackbarController = snackbarController
        )
    }
}
