package com.mitch.my_unibo.features.virtuale

import androidx.room.Dao
import androidx.room.Query
import com.mitch.my_unibo.features.course.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentCourseDao {

    @Query(
        """
        SELECT *
        FROM courses
        WHERE idCourse in (SELECT idCourse
                            FROM studentCourses
                            WHERE idStudent = :idStudent)
    """
    )
    fun getCoursesByStudentId(idStudent: Int): Flow<List<Course>>

    @Query("INSERT INTO studentCourses (idStudent, idCourse) VALUES (:idStudent, :idCourse)")
    suspend fun addCourseToStudent(idCourse: Int, idStudent: Int)

    @Query("DELETE FROM studentCourses where idStudent = :idStudent AND idCourse = :idCourse")
    suspend fun removeCourseToStudent(idCourse: Int, idStudent: Int)
}
