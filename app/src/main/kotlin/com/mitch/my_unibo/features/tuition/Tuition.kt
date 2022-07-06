package com.mitch.my_unibo.features.tuition

import java.math.BigDecimal
import java.time.LocalDate

data class Tuition(
    val name: String,
    val year: Int,
    val amount: BigDecimal,
    val dueDate: LocalDate,
    val paidDate: LocalDate
)
