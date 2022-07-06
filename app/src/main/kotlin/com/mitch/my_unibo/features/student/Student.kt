package com.mitch.my_unibo.features.student

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val idStudent: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
