package com.mitch.my_unibo.features.tuition

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentTuitionDao {

    @Query("SELECT * from studentTuition where idStudent = :idStudent")
    fun tuitionByStudentId(idStudent: Int): Flow<List<StudentTuition>>

//    @Query("INSERT INTO StudentTuition")
//    suspend fun addTuitionToStudent(idStudent: Int)

//    @Update
//    fun updateTuition(studentTuition: StudentTuition)
}
