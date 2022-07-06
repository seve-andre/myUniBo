package com.mitch.my_unibo.ui.custom.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mitch.my_unibo.ui.custom.buttons.CloseButton
import com.mitch.my_unibo.ui.custom.text.Title

@Composable
fun InfoDialog(
    title: String,
    onDismissRequest: () -> Unit,
    content: @Composable ((RowScope).() -> Unit)
) {
    Dialog(onDismissRequest = onDismissRequest) {
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
            }
        }
    }
}
