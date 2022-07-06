package com.mitch.my_unibo.ui.courses.components.virtuale

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.course.Course
import com.mitch.my_unibo.features.course.CourseRepo
import com.mitch.my_unibo.features.student.StudentRepo
import com.mitch.my_unibo.features.virtuale.StudentCourseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class VirtualeViewModel @Inject constructor(
    private val studentCourseRepo: StudentCourseRepo,
    private val studentRepo: StudentRepo,
    private val courseRepo: CourseRepo,
    private val userDataStore: UserDataStore
) : ViewModel() {

    private var idStudent by mutableStateOf(0)

    init {
        runBlocking {
            val email = userDataStore.getEmail()
            idStudent = studentRepo.getStudentByEmail(email)?.idStudent ?: 0
        }
    }

    fun findStudentCourses(): List<Course> {
        val courses = MutableStateFlow(listOf<Course>())

        runBlocking {
            courses.value =
                if (idStudent == 0) emptyList()
                else studentCourseRepo.getCoursesByStudentId(idStudent).first()
        }

        return courses.value
    }

    fun findAllCourses(): List<Course> {
        val courses = MutableStateFlow(listOf<Course>())
        runBlocking {
            courses.value = courseRepo.getCourses().first()
        }
        return courses.value
    }

    fun findCoursesToAdd(): List<Course> {
        val studentCoursesIds = MutableStateFlow(listOf<Int>())
        val allCourses = this.findAllCourses()

        runBlocking {
            studentCoursesIds.value = studentCourseRepo
                .getCoursesByStudentId(idStudent)
                .first()
                .map { course -> course.idCourse!! }
        }

        return allCourses.filter { course -> course.idCourse !in studentCoursesIds.value }
    }

    fun findCoursesLike(pattern: String): List<Course> {
        val courses = MutableStateFlow(listOf<Course>())
        runBlocking {
            courses.value = courseRepo.getCoursesLike(pattern).first()
        }
        return courses.value
    }

    fun findCourseById(idCourse: Int): Course? {
        val course = MutableStateFlow<Course?>(null)
        runBlocking {
            course.value = courseRepo.getCourseById(idCourse)
        }
        return course.value
    }

    fun enrollStudentToCourse(idCourse: Int) {
        viewModelScope.launch {
            studentCourseRepo.addCourseToStudent(idCourse, idStudent)
        }
    }

    fun removeCourseToStudent(idCourse: Int) {
        viewModelScope.launch {
            studentCourseRepo.removeCourseToStudent(idCourse, idStudent)
        }
    }
}
