package com.mitch.my_unibo.ui.exams.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitch.my_unibo.R
import com.mitch.my_unibo.features.exam.stats.StatsCalculator
import com.mitch.my_unibo.ui.custom.dividers.VerticalDivider
import com.mitch.my_unibo.ui.custom.progress.TextRoundedCircularProgress
import com.mitch.my_unibo.ui.custom.text.Title
import com.mitch.my_unibo.ui.theme.UniBoColor
import com.mitch.my_unibo.ui.theme.custom.padding
import java.util.*

@Composable
fun StatsCard(
    studentExamsViewModel: StudentExamsViewModel
) {
    val statsCalculator = studentExamsViewModel.statsCalculator

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Title(text = stringResource(R.string.stats))

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = padding.medium, vertical = padding.large),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${statsCalculator.gpa()}",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onSurface
                            )
                            Text(
                                text = stringResource(R.string.gpa),
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = String.format(
                                    // uses dot as decimal separator
                                    locale = Locale.US,
                                    // just 2 decimals
                                    format = "%.2f",
                                    statsCalculator.startingGrade()
                                ),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onSurface
                            )

                            Text(
                                text = stringResource(R.string.starting_grade),
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                    TextRoundedCircularProgress(
                        progress = statsCalculator.percentageTakenCfu(),
                        color = UniBoColor,
                        strokeWidth = 25.dp,
                        text = "di CFU totali"
                    )
                    Caption(statsCalculator)
                }
            }
        }
    }
}

@Composable
fun Caption(
    statsCalculator: StatsCalculator
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(UniBoColor)
                    )
                    Text(
                        text = "${statsCalculator.takenCfu()} CFU sostenuti",
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEBEBEB))
                    )
                    Text(
                        text = "${statsCalculator.notTakenCfu()} CFU da sostenere",
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
        VerticalDivider(height = 60.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Totale:",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = "${statsCalculator.totalCfu()} CFU",
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun StatsPreview() {
//    Caption()
}
