package com.mitch.my_unibo.features.exam

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mitch.my_unibo.features.course.Course
import java.time.LocalDate

@Entity(tableName = "exams")
data class Exam(
    @PrimaryKey(autoGenerate = true) val idExam: Int? = null,
    @Embedded val course: Course,
    val date: LocalDate
)
