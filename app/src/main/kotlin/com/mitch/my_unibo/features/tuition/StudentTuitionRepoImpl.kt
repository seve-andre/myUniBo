package com.mitch.my_unibo.features.tuition

import kotlinx.coroutines.flow.Flow

class StudentTuitionRepoImpl(
    private val dao: StudentTuitionDao
) : StudentTuitionRepo {
    override fun getStudentTuition(idStudent: Int): Flow<List<StudentTuition>> {
        return dao.tuitionByStudentId(idStudent)
    }
}
