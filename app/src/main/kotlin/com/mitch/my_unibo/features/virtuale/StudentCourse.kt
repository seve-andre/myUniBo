package com.mitch.my_unibo.features.virtuale

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mitch.my_unibo.features.course.Course
import com.mitch.my_unibo.features.student.Student

@Entity(
    tableName = "studentCourses",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("idStudent"),
            childColumns = arrayOf("idStudent"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Course::class,
            parentColumns = arrayOf("idCourse"),
            childColumns = arrayOf("idCourse"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudentCourse(
    @PrimaryKey val id: Int,
    val idStudent: Int,
    val idCourse: Int
)
