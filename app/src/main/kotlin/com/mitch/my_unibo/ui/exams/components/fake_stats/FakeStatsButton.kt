package com.mitch.my_unibo.ui.exams.components.fake_stats

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton

@Composable
fun FakeStatsButton(onClick: () -> Unit) {
    PrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = "CALCOLA MEDIA IPOTETICA")
    }
}
