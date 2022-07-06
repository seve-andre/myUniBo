package com.mitch.my_unibo.ui.profile.components.badge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.capitalize
import com.mitch.my_unibo.ui.profile.components.StudentViewModel
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.util.SecondaryScreen

@Composable
fun BadgeScreen(
    studentViewModel: StudentViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    val student = studentViewModel.getLoggedStudent()

    SecondaryScreen(
        title = stringResource(id = R.string.badge).capitalize(),
        navController = navController,
        systemController = systemController
    ) {
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
                )
            ) {
                BadgeCard(student = student!!)
            }
        }
    }
}
