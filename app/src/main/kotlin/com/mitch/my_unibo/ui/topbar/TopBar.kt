package com.mitch.my_unibo.ui.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.ui.custom.icons.BadgeIcon
import com.mitch.my_unibo.ui.navigation.Screen

@Composable
fun TopBar(
    navController: NavHostController
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 0.dp,
        contentPadding = PaddingValues(10.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            NotificationsButton {
                navController.navigate(Screen.SeeNotifications.route)
            }
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            UniBoLogo()
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            EmailButton {
                navController.navigate(Screen.SeeEmails.route)
            }
        }
    }
}

@Composable
private fun UniBoLogo() {
    Text(
        text = stringResource(id = R.string.app_name),
        color = MaterialTheme.colors.primary,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun NotificationsButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        BadgeIcon(
            idIcon = R.drawable.ic_notifications_bell,
            iconDescription = "see app notifications",
            showBadge = false
        )
    }
}

@Composable
private fun EmailButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        BadgeIcon(
            idIcon = R.drawable.ic_emails,
            iconDescription = "see uni emails",
            showBadge = false
        )
    }
}


@Preview
@Composable
fun TopBarPreview() {
//    TopBar()
}
