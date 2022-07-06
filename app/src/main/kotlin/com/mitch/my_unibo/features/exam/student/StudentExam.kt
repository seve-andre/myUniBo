package com.mitch.my_unibo.features.exam.student

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mitch.my_unibo.features.exam.Exam
import com.mitch.my_unibo.features.student.Student
import java.time.LocalDate

@Entity(
    tableName = "studentExams",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("idStudent"),
            childColumns = arrayOf("idStudent"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Exam::class,
            parentColumns = arrayOf("idExam"),
            childColumns = arrayOf("idExam"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudentExam(
    @PrimaryKey val id: Int? = null,
    val idStudent: Int,
    val idExam: Int,
    val grade: Int,
    val date: LocalDate,
    val booked: Boolean
)
