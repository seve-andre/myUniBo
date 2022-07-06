package com.mitch.my_unibo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mitch.my_unibo.datastore.SettingsDataStore
import com.mitch.my_unibo.ui.util.AppLanguage
import com.mitch.my_unibo.ui.bottom_navbar.BottomNavBar
import com.mitch.my_unibo.ui.custom.snackbar.SimpleSnackbar
import com.mitch.my_unibo.ui.navigation.NavigationGraph
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.theme.AppTheme
import com.mitch.my_unibo.ui.theme.MyUniBoTheme
import com.mitch.my_unibo.ui.topbar.TopBar
import com.mitch.my_unibo.ui.util.SnackbarController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val theme: MutableState<AppTheme> = remember { mutableStateOf(AppTheme.Light) }

            // sets up activity with language and theme preferences saved in the settings datastore
            lifecycleScope.launchWhenCreated {
                AppLanguage.setLanguage(context, SettingsDataStore(context).getLanguage())
                theme.value = AppTheme.themeFromId(SettingsDataStore(context).getThemeId())
            }

            MyUniBoTheme(theme = theme.value) {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val scaffoldState = rememberScaffoldState()
                val systemController = rememberSystemUiController()
                val snackBarController = SnackbarController(lifecycleScope)

                // show top and bottom bar only in certain screens
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    scaffoldState = scaffoldState,
                    topBar = {
                        if (shouldShowTopBar(backStackEntry)) {
                            TopBar(navController)
                        }
                    },
                    bottomBar = {
                        if (shouldShowBottomBar(backStackEntry)) {
                            BottomNavBar(navController)
                        }
                    },
                    snackbarHost = { scaffoldState.snackbarHostState }
                ) { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = if (shouldShowBottomBar(backStackEntry))
                                    padding.calculateBottomPadding() else 0.dp
                            )
                    ) {
                        NavigationGraph(
                            navController = navController,
                            systemController = systemController,
                            scaffoldState = scaffoldState,
                            snackbarController = snackBarController
                        )
                        SimpleSnackbar(
                            snackbarHostState = scaffoldState.snackbarHostState,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }

    private fun shouldShowTopBar(backStackEntry: NavBackStackEntry?): Boolean {
        return backStackEntry?.destination?.route in listOf(
            Screen.Courses.route,
            Screen.Exams.route,
            Screen.Profile.route
        )
    }

    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        return backStackEntry?.destination?.route in listOf(
            Screen.Courses.route,
            Screen.Exams.route,
            Screen.Profile.route
        )
    }
}
