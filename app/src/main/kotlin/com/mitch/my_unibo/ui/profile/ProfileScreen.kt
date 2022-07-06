package com.mitch.my_unibo.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.profile.components.LogoutButton
import com.mitch.my_unibo.ui.profile.components.ProfileInfo
import com.mitch.my_unibo.ui.profile.components.ProfileSettings
import com.mitch.my_unibo.ui.profile.components.StudentUseful
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.theme.isThemeDark
import com.mitch.my_unibo.ui.util.SnackbarController
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileScreen(
    navController: NavHostController,
    systemController: SystemUiController,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController
) {
    systemController.setStatusBarColor(
        MaterialTheme.colors.surface,
        darkIcons = !isThemeDark()
    )

    systemController.setNavigationBarColor(
        color = UniBoColor,
        darkIcons = false
    )

    val context = LocalContext.current

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
            ProfileInfo(
                scaffoldState = scaffoldState,
                snackbarController = snackbarController
            )
            StudentUseful(navController = navController)
            ProfileSettings(navController = navController)
            LogoutButton {
                runBlocking {
                    UserDataStore(context).logout()
                }
                navController.backQueue.clear()
                navController.navigate(Screen.Login.route)
            }

        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
//    ProfileScreen()
}
