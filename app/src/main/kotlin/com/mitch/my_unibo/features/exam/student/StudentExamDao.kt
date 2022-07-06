package com.mitch.my_unibo.features.exam.student

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentExamDao {

    @Query(
        """
        SELECT *
            FROM studentExams
            WHERE idStudent = :idStudent
    """
    )
    fun getExamsByStudentId(idStudent: Int): Flow<List<StudentExam>>

    @Insert
    suspend fun addExam(studentExam: StudentExam)

    @Delete
    suspend fun removeExam(studentExam: StudentExam)
}
