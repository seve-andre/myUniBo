package com.mitch.my_unibo.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Courses : Screen("courses")
    object Exams : Screen("exams")
    object Profile : Screen("profile")
    object SeeNotifications : Screen("notifications")
    object SeeEmails : Screen("emails")
    object SearchCourse : Screen("searchCourse")
    object SeeCourse : Screen("seeCourse")
    object ChooseExamsToBook : Screen("chooseExamsToBook")
    object BookExam : Screen("bookExam")
    object SeeUpcomingExams : Screen("upcomingExams")
    object SeeTakenExams : Screen("takenExams")
    object SeeGrades : Screen("seeGrades")
    object SeeTuition : Screen("seeTuition")
    object SeeBadge : Screen("seeBadge")
    object SeeSetting : Screen("setting")
    object FakeStats : Screen("fakeStats")
}
