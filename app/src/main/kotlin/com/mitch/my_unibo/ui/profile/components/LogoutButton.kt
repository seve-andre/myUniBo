package com.mitch.my_unibo.ui.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton

@Composable
fun LogoutButton(
    onClick: () -> Unit
) {
    PrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = "LOGOUT")
    }
}

@Preview
@Composable
fun LogoutButtonPreview() {
    LogoutButton {}
}
