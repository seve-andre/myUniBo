package com.mitch.my_unibo.features.lesson

import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

interface LessonRepo {
    fun getLessonsByDay(day: LocalDate): Flow<List<Lesson>>
}
