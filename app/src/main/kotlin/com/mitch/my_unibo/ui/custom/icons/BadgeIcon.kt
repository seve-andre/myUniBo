package com.mitch.my_unibo.ui.custom.icons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.R

@Composable
fun BadgeIcon(
    idIcon: Int,
    iconDescription: String,
    showBadge: Boolean
) {
    BadgedBox(
        badge = {
            if (showBadge) {
                Badge(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape),
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )
            }
        }
    ) {
        Icon(
            painter = painterResource(id = idIcon),
            contentDescription = iconDescription,
            tint = MaterialTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
fun BadgeIconPreview() {
    Row(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BadgeIcon(
            idIcon = R.drawable.ic_notifications_bell,
            iconDescription = "see app notifications",
            showBadge = true
        )
        BadgeIcon(
            idIcon = R.drawable.ic_baseline_settings_24,
            iconDescription = "see app notifications",
            showBadge = false
        )
        BadgeIcon(
            idIcon = R.drawable.ic_baseline_settings_24,
            iconDescription = "see emails",
            showBadge = false
        )
    }
}
