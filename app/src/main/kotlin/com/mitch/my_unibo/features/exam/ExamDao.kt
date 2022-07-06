package com.mitch.my_unibo.features.exam

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamDao {
    @Query("SELECT * FROM exams")
    fun getExams(): Flow<List<Exam>>

    @Query(
        """
            SELECT cfu
            FROM courses
            WHERE idCourse
            IN (SELECT idCourse
                FROM exams
                WHERE idExam = :idExam
            )
        """
    )
    suspend fun getCfuFromExam(idExam: Int): Int
}
