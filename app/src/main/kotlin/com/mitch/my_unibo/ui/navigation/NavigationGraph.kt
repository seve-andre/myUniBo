package com.mitch.my_unibo.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.ui.authentication.LoginScreen
import com.mitch.my_unibo.ui.authentication.RegisterScreen
import com.mitch.my_unibo.ui.courses.CoursesScreen
import com.mitch.my_unibo.ui.courses.components.virtuale.SearchCourseScreen
import com.mitch.my_unibo.ui.courses.components.virtuale.VirtualeCourseScreen
import com.mitch.my_unibo.ui.exams.ExamsScreen
import com.mitch.my_unibo.ui.exams.components.book_exam.BookExamScreen
import com.mitch.my_unibo.ui.exams.components.book_exam.ChooseExamsToBookScreen
import com.mitch.my_unibo.ui.exams.components.booked_exams.BookedExamsScreen
import com.mitch.my_unibo.ui.exams.components.fake_stats.FakeStatsScreen
import com.mitch.my_unibo.ui.exams.components.taken_exams.TakenExamsScreen
import com.mitch.my_unibo.ui.navigation.parcelables.BookExamsParcelable
import com.mitch.my_unibo.ui.profile.ProfileScreen
import com.mitch.my_unibo.ui.profile.components.badge.BadgeScreen
import com.mitch.my_unibo.ui.profile.components.grades.GradesScreen
import com.mitch.my_unibo.ui.profile.components.settings.SettingScreen
import com.mitch.my_unibo.ui.profile.components.tuition.TuitionScreen
import com.mitch.my_unibo.ui.splash.SplashScreen
import com.mitch.my_unibo.ui.topbar.email.EmailScreen
import com.mitch.my_unibo.ui.topbar.notifications.NotificationScreen
import com.mitch.my_unibo.ui.util.SnackbarController

@Composable
fun NavigationGraph(
    navController: NavHostController,
    systemController: SystemUiController,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
                navController = navController,
                systemController = systemController,
                scaffoldState = scaffoldState,
                snackbarController = snackbarController
            )
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.Courses.route) {
            CoursesScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(
            route = "${Screen.SeeCourse.route}/{idCourse}",
            arguments = listOf(navArgument("idCourse") { type = NavType.IntType })
        ) { backStackEntry ->
            VirtualeCourseScreen(
                navController = navController,
                systemController = systemController,
                idCourse = backStackEntry.arguments?.getInt("idCourse")
            )
        }
        composable(route = Screen.SearchCourse.route) {
            SearchCourseScreen(
                navController = navController,
                systemController = systemController,
                scaffoldState = scaffoldState,
                snackbarController = snackbarController
            )
        }
        composable(route = Screen.SeeNotifications.route) {
            NotificationScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.SeeEmails.route) {
            EmailScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.Exams.route) {
            ExamsScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.ChooseExamsToBook.route) {
            ChooseExamsToBookScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.BookExam.route) {
            val examsParcelable =
                navController.previousBackStackEntry?.arguments?.getParcelable<BookExamsParcelable>(
                    "examsToBook"
                )

            if (examsParcelable != null) {
                BookExamScreen(
                    navController = navController,
                    systemController = systemController,
                    scaffoldState = scaffoldState,
                    snackbarController = snackbarController,
                    examsIds = examsParcelable.examsToBook
                )
            }
        }
        composable(route = Screen.SeeUpcomingExams.route) {
            BookedExamsScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.SeeTakenExams.route) {
            TakenExamsScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                navController = navController,
                systemController = systemController,
                scaffoldState = scaffoldState,
                snackbarController = snackbarController
            )
        }
        composable(route = Screen.SeeGrades.route) {
            GradesScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.SeeTuition.route) {
            TuitionScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.SeeBadge.route) {
            BadgeScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = Screen.FakeStats.route) {
            FakeStatsScreen(
                navController = navController,
                systemController = systemController
            )
        }
        composable(route = "${Screen.SeeSetting.route}/{idSetting}",
            arguments = listOf(navArgument("idSetting") { type = NavType.IntType })
        ) { backStackEntry ->
            SettingScreen(
                idSetting = backStackEntry.arguments?.getInt("idSetting"),
                navController = navController,
                systemController = systemController
            )
        }
    }
}
