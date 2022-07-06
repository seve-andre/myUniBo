package com.mitch.my_unibo.ui.bottom_navbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mitch.my_unibo.ui.bottom_navbar.components.NavBarItem

@Composable
fun BottomNavBar(navController: NavController) {
    val navbarItems = listOf(
        NavBarItem.Courses,
        NavBarItem.Exams,
        NavBarItem.Profile
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navbarItems.forEach { navBarItem -> AddItem(navBarItem, currentRoute, navController) }
    }
}

@Composable
fun RowScope.AddItem(
    navBarItem: NavBarItem,
    currentRoute: String?,
    navController: NavController
) {
    val selected = currentRoute == navBarItem.route
    BottomNavigationItem(
        icon = {
            Icon(
                painter = painterResource(
                    id = if (selected) navBarItem.idFilledIcon else navBarItem.idOutlinedIcon
                ),
                contentDescription = stringResource(id = navBarItem.idItemName)
            )
        },
        label = {
            Text(
                text = stringResource(id = navBarItem.idItemName),
                maxLines = 1,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        },
        selectedContentColor = Color.White,
        unselectedContentColor = Color.White.copy(0.4f),
        alwaysShowLabel = true,
        selected = selected,
        onClick = {
            navController.navigate(navBarItem.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                // Avoid multiple copies of the same destination when
                // re-selecting the same item
                launchSingleTop = true
                // Restore state when re-selecting a previously selected item
                restoreState = true
            }
        }
    )
}
