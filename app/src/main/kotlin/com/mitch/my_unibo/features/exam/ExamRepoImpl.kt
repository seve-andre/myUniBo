package com.mitch.my_unibo.features.exam

import kotlinx.coroutines.flow.Flow

class ExamRepoImpl(
    private val dao: ExamDao
) : ExamRepo {

    override fun getExams(): Flow<List<Exam>> {
        return dao.getExams()
    }

    override suspend fun getCfuFromExam(idExam: Int): Int {
        return dao.getCfuFromExam(idExam)
    }
}
