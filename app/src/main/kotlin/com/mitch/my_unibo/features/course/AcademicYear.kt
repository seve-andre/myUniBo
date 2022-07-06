package com.mitch.my_unibo.features.course

import java.time.Year

data class AcademicYear(
    val startYear: Year,
    val endYear: Year
) {
    init {
        if (startYear.isAfter(endYear)) {
            throw IllegalArgumentException("Start year can't be after end year.")
        }
    }
}
