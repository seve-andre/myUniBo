package com.mitch.my_unibo.features.lesson

import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

class LessonRepoImpl(
    private val dao: LessonDao
) : LessonRepo {

    override fun getLessonsByDay(day: LocalDate): Flow<List<Lesson>> {
        return dao.getLessonsByDay(day)
    }
}
