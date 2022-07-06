package com.mitch.my_unibo.ui.bottom_navbar.components

import com.mitch.my_unibo.R
import com.mitch.my_unibo.ui.navigation.Screen

/**
 * Represents each restricted item that composes the navigation bar (aka tab bar).
 * @property route - for navigation between items
 * @property idItemName - label associated to the icon
 * @property idOutlinedIcon - used when the item is not focused
 * @property idFilledIcon - used only when the item is focused
 */
sealed class NavBarItem(
    val route: String,
    val idItemName: Int,
    val idOutlinedIcon: Int,
    val idFilledIcon: Int
) {
    /**
     * Courses tab is the one that is focused when application starts.
     * It focuses on courses and their lessons during the week.
     *
     * It has a lot of great features, like:
     *  - today's lessons and the possibility to show a different day
     *  - *virtuale* courses - to see them you have to be enrolled to the ones you are interested in
     *  - add new courses (you can enroll only to the ones in your plan)
     *  - read info about teachers, tutors and courses reviews
     *  - app notifications and outlook institutional e-mail address
     */
    object Courses : NavBarItem(
        route = Screen.Courses.route,
        idItemName = R.string.nav_item_courses,
        idOutlinedIcon = R.drawable.ic_book_outlined_white,
        idFilledIcon = R.drawable.ic_book_filled_white
    )

    /**
     * Exams tab gives you the possibility to:
     *  - book exams
     *  - see taken and upcoming exams
     *  - see stats about student career
     */
    object Exams : NavBarItem(
        route = Screen.Exams.route,
        idItemName = R.string.nav_item_exams,
        idOutlinedIcon = R.drawable.ic_edit_outlined,
        idFilledIcon = R.drawable.ic_edit_filled
    )

    /**
     * Profile tab has a lot of features too, like:
     *  - see student name, email institutional address and ID
     *  - see tuition, virtual badge and gpa
     *  - change app theme and language
     *  - other info about uni
     */
    object Profile : NavBarItem(
        route = Screen.Profile.route,
        idItemName = R.string.nav_item_profile,
        idOutlinedIcon = R.drawable.ic_user_outlined_white,
        idFilledIcon = R.drawable.ic_user_filled_white
    )
}
