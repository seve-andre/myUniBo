package com.mitch.my_unibo.features.exam

import kotlinx.coroutines.flow.Flow

interface ExamRepo {
    fun getExams(): Flow<List<Exam>>

    suspend fun getCfuFromExam(idExam: Int): Int
}
