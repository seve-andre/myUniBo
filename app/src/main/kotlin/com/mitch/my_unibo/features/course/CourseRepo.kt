package com.mitch.my_unibo.features.course

import kotlinx.coroutines.flow.Flow

interface CourseRepo {

    suspend fun getCourseById(idCourse: Int): Course?

    fun getCourses(): Flow<List<Course>>

    fun getCoursesLike(pattern: String): Flow<List<Course>>
}
