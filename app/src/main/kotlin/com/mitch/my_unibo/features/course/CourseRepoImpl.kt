package com.mitch.my_unibo.features.course

import kotlinx.coroutines.flow.Flow

class CourseRepoImpl(
    private val dao: CourseDao
): CourseRepo {

    override suspend fun getCourseById(idCourse: Int): Course? {
        return dao.getCourseById(idCourse)
    }

    override fun getCourses(): Flow<List<Course>> {
        return dao.getCourses()
    }

    override fun getCoursesLike(pattern: String): Flow<List<Course>> {
        return dao.getCoursesLike(pattern)
    }
}
