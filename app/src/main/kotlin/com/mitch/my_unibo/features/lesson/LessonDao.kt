package com.mitch.my_unibo.features.lesson

import androidx.room.Dao
import androidx.room.Query
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons where day = :day")
    fun getLessonsByDay(day: LocalDate): Flow<List<Lesson>>
}
