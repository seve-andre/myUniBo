package com.mitch.my_unibo.ui.custom.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mitch.my_unibo.ui.theme.custom.padding

@Composable
fun SimpleSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.onSurface,
    onActionClick: (() -> Unit)? = null
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(padding.medium),
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(
                            onClick = {
                                onActionClick?.invoke()
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        ) {
                            Text(
                                text = actionLabel,
                                color = Color.White
                            )
                        }
                    }
                },
                backgroundColor = backgroundColor
            ) {
                Text(
                    text = data.message,
                    color = Color.White
                )
            }
        },
        modifier = modifier
    )
}
