package com.mitch.my_unibo.ui.courses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.ui.courses.components.lessons.LessonsOfTheDay
import com.mitch.my_unibo.ui.courses.components.virtuale.VirtualeCourses
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.theme.isThemeDark

@Composable
fun CoursesScreen(
    navController: NavHostController,
    systemController: SystemUiController
) {
    systemController.setStatusBarColor(
        color = MaterialTheme.colors.surface,
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
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            LessonsOfTheDay()
            VirtualeCourses(navController = navController)
        }
    }
}
