package com.mitch.my_unibo.ui.custom.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mitch.my_unibo.ui.custom.buttons.CloseButton
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton
import com.mitch.my_unibo.ui.custom.text.Title

/**
 * TODO
 *
 * See Also: [Material dialogs](https://material.io/components/dialogs#usage)
 *
 * @param onDismissRequest
 * @param onConfirmRequest
 * @param content
 */
@Composable
fun ConfirmationDialog(
    title: String,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    content: @Composable (RowScope.() -> Unit)
) {
    Dialog(onDismissRequest = onDismissRequest) {
        DialogContent(title, onDismissRequest, onConfirmRequest, content)
    }
}

@Composable
private fun DialogContent(
    title: String,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    content: @Composable (RowScope.() -> Unit)
) {
    Card {
        Column(
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                top = 10.dp,
                bottom = 30.dp
            ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Title(
                    text = title,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 10.dp)
                )
                CloseButton(
                    onClick = onDismissRequest,
                    modifier = Modifier.align(Alignment.Top)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                content = content
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                TextButton(
                    onClick = onDismissRequest,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Text(text = "ANNULLA")
                }
                PrimaryButton(
                    onClick = onConfirmRequest,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    color = MaterialTheme.colors.secondary
                ) {
                    Text(text = "CAMBIA")
                }
            }
        }
    }
}

@Preview
@Composable
fun ConfirmationDialogPreview() {
    DialogContent(
        title = "ciao",
        onDismissRequest = { },
        onConfirmRequest = { }
    ) {

    }
}
