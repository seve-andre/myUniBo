package com.mitch.my_unibo.ui.profile.components.tuition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.extensions.capitalize
import com.mitch.my_unibo.features.tuition.StudentTuition
import com.mitch.my_unibo.ui.custom.cards.ExpandableCard
import com.mitch.my_unibo.ui.exams.components.ExamsCategory
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.util.SecondaryScreen
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun TuitionScreen(
    viewModel: TuitionViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    SecondaryScreen(
        title = stringResource(R.string.tuition).capitalize(),
        navController = navController,
        systemController = systemController
    ) {
        Column {
            val examsCategory = listOf(
                ExamsCategory.FirstYear,
                ExamsCategory.SecondYear,
                ExamsCategory.ThirdYear
            )

            examsCategory.forEach { category ->
                ExpandableCard(title = category.name) {
                    LazyColumn {
                        // show taxes for each year
                        val tuitionPerYear = viewModel.tuitionInYear(category.year)
                        items(tuitionPerYear) { tuition ->
                            TuitionRow(tuition)
                            if (tuition != tuitionPerYear.last()) {
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun TuitionRow(
    studentTuition: StudentTuition
) {
    val dayMonthYearDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

    Row(
        modifier = Modifier.padding(padding.medium),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = studentTuition.tuition.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Pagamento:",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = studentTuition.tuition.paidDate.format(dayMonthYearDateFormatter))
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Scadenza:",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = studentTuition.tuition.dueDate.format(dayMonthYearDateFormatter))
                }
            }
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "${studentTuition.tuition.amount.toPlainString()} €",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (studentTuition.paid) Color.Green else Color.Red)
                    )
                    Text(
                        text = if (studentTuition.paid) "pagata" else "non pagata",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
