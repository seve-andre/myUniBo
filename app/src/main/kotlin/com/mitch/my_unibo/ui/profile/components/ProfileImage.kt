package com.mitch.my_unibo.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.R

@Composable
fun ProfileImage(
    idProfileImage: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = idProfileImage),
            contentDescription = contentDescription,
            tint = Color.Black
        )
    }
}

@Preview
@Composable
fun ProfileImagePreview() {
    ProfileImage(
        idProfileImage = R.drawable.ic_user_outlined_white,
        contentDescription = "user profile image"
    )
}
