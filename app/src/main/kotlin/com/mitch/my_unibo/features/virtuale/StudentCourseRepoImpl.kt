package com.mitch.my_unibo.features.virtuale

import com.mitch.my_unibo.features.course.Course
import kotlinx.coroutines.flow.Flow

class StudentCourseRepoImpl(
    private val dao: StudentCourseDao
) : StudentCourseRepo {

    override fun getCoursesByStudentId(idStudent: Int): Flow<List<Course>> {
        return dao.getCoursesByStudentId(idStudent)
    }

    override suspend fun addCourseToStudent(idCourse: Int, idStudent: Int) {
        dao.addCourseToStudent(idCourse, idStudent)
    }

    override suspend fun removeCourseToStudent(idCourse: Int, idStudent: Int) {
        dao.removeCourseToStudent(idCourse, idStudent)
    }
}
