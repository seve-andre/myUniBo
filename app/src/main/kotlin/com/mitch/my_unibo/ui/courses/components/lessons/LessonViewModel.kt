package com.mitch.my_unibo.ui.courses.components.lessons

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.lesson.Lesson
import com.mitch.my_unibo.features.lesson.LessonRepo
import com.mitch.my_unibo.features.student.StudentRepo
import com.mitch.my_unibo.features.virtuale.StudentCourseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val lessonRepo: LessonRepo,
    private val studentCourseRepo: StudentCourseRepo,
    private val studentRepo: StudentRepo,
    private val userDataStore: UserDataStore
) : ViewModel() {

    /**
     * Gets lessons only for the courses the student is enrolled to.
     *
     * @param day of the lessons to find
     * @return list of lessons for that day
     */
    fun findLessonsByDay(day: LocalDate): List<Lesson> {
        var lessons by mutableStateOf(emptyList<Lesson>())
        var studentCoursesIds by mutableStateOf(emptyList<Int>())

        runBlocking {
            lessons = lessonRepo.getLessonsByDay(day).first()
            studentCoursesIds = studentCourseRepo
                .getCoursesByStudentId(
                    studentRepo.getStudentByEmail(userDataStore.getEmail())!!.idStudent!!
                )
                .first()
                .map { course -> course.idCourse!! }
        }

        return lessons.filter { lesson -> lesson.course.idCourse in studentCoursesIds }
    }
}
