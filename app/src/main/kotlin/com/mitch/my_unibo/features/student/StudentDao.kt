package com.mitch.my_unibo.features.student

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM students WHERE idStudent = :idStudent")
    suspend fun getStudentById(idStudent: Int): Student?

    @Query("SELECT * from students where email = :email")
    suspend fun getStudentByEmail(email: String): Student?

    @Insert
    suspend fun addStudent(student: Student)
}
