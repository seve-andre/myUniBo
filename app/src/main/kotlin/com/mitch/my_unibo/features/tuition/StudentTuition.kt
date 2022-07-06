package com.mitch.my_unibo.features.tuition

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mitch.my_unibo.features.student.Student

@Entity(
    tableName = "studentTuition",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("idStudent"),
            childColumns = arrayOf("idStudent"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudentTuition(
    @PrimaryKey(autoGenerate = true) val idStudentTuition: Int? = null,
    val idStudent: Int,
    @Embedded val tuition: Tuition,
    val paid: Boolean = false
)
