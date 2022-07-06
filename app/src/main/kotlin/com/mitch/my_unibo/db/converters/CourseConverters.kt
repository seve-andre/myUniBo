package com.mitch.my_unibo.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mitch.my_unibo.features.course.AcademicYear
import com.mitch.my_unibo.features.course.CourseRating
import com.mitch.my_unibo.features.email.Receiver
import com.mitch.my_unibo.features.professor.Professor
import java.time.Year

class CourseConverters {
    private val gson = Gson()

    @TypeConverter
    fun convertProfessors(professorsList: List<Professor>): String = gson.toJson(professorsList)

    @TypeConverter
    fun convertToProfessors(professorsJson: String): List<Professor> =
        gson.fromJson(professorsJson, Array<Professor>::class.java).toList()

    @TypeConverter
    fun convertReceivers(receiversList: List<Receiver>): String = gson.toJson(receiversList)

    @TypeConverter
    fun convertToReceivers(receiversJson: String): List<Receiver> =
        gson.fromJson(receiversJson, Array<Receiver>::class.java).toList()

    @TypeConverter
    fun convertAcademicYear(academicYear: AcademicYear): String =
        "${academicYear.startYear.value}-${academicYear.endYear.value}"

    @TypeConverter
    fun convertToAcademicYear(academicYearString: String): AcademicYear {
        val academicYearList = academicYearString.split("-")
        return AcademicYear(
            Year.of(academicYearList[0].toInt()),
            Year.of(academicYearList[1].toInt())
        )
    }

    @TypeConverter
    fun convertCourseRating(rating: CourseRating): Float = rating.value

    @TypeConverter
    fun convertToCourseRating(rating: Float): CourseRating = CourseRating(rating)
}
