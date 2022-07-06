package com.mitch.my_unibo.features.course

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mitch.my_unibo.features.professor.Professor

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey(autoGenerate = true) val idCourse: Int? = null,
    val name: String,
    val cfu: Int,
    val professors: List<Professor>,
    val yearOfTeaching: Int,
    val academicYear: AcademicYear,
    val rating: CourseRating = CourseRating(0.0f)
)
