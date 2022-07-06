package com.mitch.my_unibo.features.tuition

import kotlinx.coroutines.flow.Flow

interface StudentTuitionRepo {
    fun getStudentTuition(idStudent: Int): Flow<List<StudentTuition>>
}
