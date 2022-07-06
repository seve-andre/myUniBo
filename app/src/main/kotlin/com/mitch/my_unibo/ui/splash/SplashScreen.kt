package com.mitch.my_unibo.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.theme.UniBoColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Composable
fun SplashScreen(
    navController: NavHostController,
    systemController: SystemUiController
) {
    val context = LocalContext.current

    val isLoggedIn = remember {
        mutableStateOf(
            runBlocking {
                UserDataStore(context).isUserLoggedIn()
            }
        )
    }

    systemController.setStatusBarColor(
        color = UniBoColor,
        darkIcons = false
    )
    systemController.setNavigationBarColor(
        color = UniBoColor,
        darkIcons = false
    )

    /**
     * Launches splash screen and after 1 second goes to the home screen if already logged in,
     * otherwise to the login screen.
     * popBackStack() is used so on back button press you can't go back to the splash screen
     * once it's loaded.
     */
    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.popBackStack(
            route = Screen.Splash.route,
            inclusive = true
        )

        if (isLoggedIn.value) {
            navController.navigate(Screen.Courses.route)
        } else {
            navController.navigate(Screen.Login.route)
        }
    }
    SplashDesign()
}

@Composable
private fun SplashDesign() {
    Box(
        modifier = Modifier
            .background(UniBoColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_grad_hat_white),
                contentDescription = "UniBO logo",
                modifier = Modifier
                    .size(120.dp),
                tint = Color.White
            )
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun SplashDesignPreview() {
    SplashDesign()
}
