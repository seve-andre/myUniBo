package com.mitch.my_unibo.features.exam.student

import kotlinx.coroutines.flow.Flow

class StudentExamRepoImpl(
    private val dao: StudentExamDao
) : StudentExamRepo {

    override fun getExamsByStudentId(idStudent: Int): Flow<List<StudentExam>> {
        return dao.getExamsByStudentId(idStudent)
    }

    override suspend fun addExam(studentExam: StudentExam) {
        dao.addExam(studentExam)
    }

    override suspend fun removeExam(studentExam: StudentExam) {
        dao.removeExam(studentExam)
    }
}
