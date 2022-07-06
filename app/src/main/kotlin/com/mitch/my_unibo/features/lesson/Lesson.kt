package com.mitch.my_unibo.features.lesson

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mitch.my_unibo.features.course.Course
import com.mitch.my_unibo.features.professor.Professor
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey(autoGenerate = true) val idLesson: Int? = null,
    @Embedded val course: Course,
    val room: String,
    val day: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime
) {

    fun professors(): List<Professor> {
        return course.professors
    }
}
