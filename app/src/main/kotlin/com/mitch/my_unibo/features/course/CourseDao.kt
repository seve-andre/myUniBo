package com.mitch.my_unibo.features.course

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM courses WHERE idCourse = :idCourse")
    suspend fun getCourseById(idCourse: Int): Course?

    @Query("SELECT * FROM courses")
    fun getCourses(): Flow<List<Course>>

    @Query("SELECT * from courses WHERE name LIKE '%' || :pattern || '%'")
    fun getCoursesLike(pattern: String): Flow<List<Course>>
}
