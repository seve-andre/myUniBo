package com.mitch.my_unibo.features.virtuale

import com.mitch.my_unibo.features.course.Course
import kotlinx.coroutines.flow.Flow

interface StudentCourseRepo {

    fun getCoursesByStudentId(idStudent: Int): Flow<List<Course>>

    suspend fun addCourseToStudent(idCourse: Int, idStudent: Int)

    suspend fun removeCourseToStudent(idCourse: Int, idStudent: Int)
}
