package com.mitch.my_unibo.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.theme.isThemeDark

@Composable
fun SecondaryScreen(
    title: String,
    navController: NavHostController,
    systemController: SystemUiController,
    includeMenu: Boolean = false,
    menuIcon: Painter = rememberVectorPainter(image = Icons.Default.MoreVert),
    onMenuClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit
) {
    systemController.setNavigationBarColor(
        color = MaterialTheme.colors.background,
        darkIcons = !isThemeDark()
    )

    systemController.setStatusBarColor(
        color = MaterialTheme.colors.surface,
        darkIcons = !isThemeDark()
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding.medium, vertical = padding.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                            /*navController.previousBackStackEntry?.destination?.route?.let { route ->
//                                navController.popBackStack()
                                navController.navigate(route)
                            }*/
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription =
                            "Go back to ${navController.previousBackStackEntry?.destination?.route}",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (includeMenu) {
                        IconButton(onClick = onMenuClick) {
                            Icon(
                                painter = menuIcon,
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            content = content
        )
    }
}

@Preview
@Composable
fun SecondaryScreenPreview() {
    TopAppBar(
        modifier = Modifier.height(80.dp)
    ) {

    }
}
