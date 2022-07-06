package com.mitch.my_unibo.features.exam.student

import kotlinx.coroutines.flow.Flow

interface StudentExamRepo {
    fun getExamsByStudentId(idStudent: Int): Flow<List<StudentExam>>
    suspend fun addExam(studentExam: StudentExam)
    suspend fun removeExam(studentExam: StudentExam)
}
