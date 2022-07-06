package com.my_unibo.model

import com.google.common.truth.Truth.assertThat
import com.mitch.my_unibo.features.course.AcademicYear
import java.time.Year
import org.junit.Test

class AcademicYearTest {

    private lateinit var academicYear: AcademicYear

    @Test(expected = IllegalArgumentException::class)
    fun `wrong academic year throws exception`() {
        academicYear = AcademicYear(Year.of(2021), Year.of(2000))
    }

    @Test fun `correct year`() {
        academicYear = AcademicYear(Year.of(2021), Year.of(2022))
        assertThat(academicYear.startYear.value).isEqualTo(2021)
        assertThat(academicYear.endYear.value).isEqualTo(2022)
    }
}
