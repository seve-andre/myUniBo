package com.mitch.my_unibo.ui.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.ui.navigation.Screen

@Composable
fun StudentUseful(
    navController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val equalWidthModifier = Modifier.weight(1f)

        GradesButton(
            onClick = { navController.navigate(Screen.SeeGrades.route) },
            modifier = equalWidthModifier
        )
        TuitionButton(
            onClick = { navController.navigate(Screen.SeeTuition.route) },
            modifier = equalWidthModifier
        )
        BadgeButton(
            onClick = { navController.navigate(Screen.SeeBadge.route) },
            modifier = equalWidthModifier
        )

    }
}

@Composable
private fun GradesButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    UsefulButton(
        onClick = onClick,
        modifier = modifier,
        idIcon = R.drawable.ic_diary,
        iconDescription = "diary symbol",
        buttonText = stringResource(R.string.grades)
    )
}

@Composable
private fun TuitionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    UsefulButton(
        onClick = onClick,
        modifier = modifier,
        idIcon = R.drawable.ic_money,
        iconDescription = "euro symbol",
        buttonText = stringResource(R.string.tuition)
    )
}

@Composable
private fun BadgeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    UsefulButton(
        onClick = onClick,
        modifier = modifier,
        idIcon = R.drawable.ic_badge,
        iconDescription = "badge icon",
        buttonText = stringResource(R.string.badge)
    )
}

@Composable
private fun UsefulButton(
    onClick: () -> Unit,
    modifier: Modifier,
    idIcon: Int,
    iconDescription: String,
    buttonText: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(110.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = idIcon),
                contentDescription = iconDescription
            )
            Text(
                text = buttonText,
                fontSize = if (buttonText.length > 10) 12.sp else TextUnit.Unspecified
            )
        }
    }
}

@Preview
@Composable
fun StudentUsefulPreview() {
//    StudentUseful()
}
