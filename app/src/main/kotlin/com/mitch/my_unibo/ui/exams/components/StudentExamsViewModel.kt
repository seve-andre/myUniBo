package com.mitch.my_unibo.ui.exams.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.exam.Exam
import com.mitch.my_unibo.features.exam.ExamRepo
import com.mitch.my_unibo.features.exam.stats.StatsCalculator
import com.mitch.my_unibo.features.exam.student.StudentExam
import com.mitch.my_unibo.features.exam.student.StudentExamRepo
import com.mitch.my_unibo.features.student.StudentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class StudentExamsViewModel @Inject constructor(
    private val studentExamsRepo: StudentExamRepo,
    private val studentRepo: StudentRepo,
    private val examRepo: ExamRepo,
    private val userDataStore: UserDataStore,
    val statsCalculator: StatsCalculator
) : ViewModel() {

    private val studentExams = MutableStateFlow(emptyList<StudentExam>())
    private val idStudent = MutableStateFlow(0)
    private val allExams = MutableStateFlow(emptyList<Exam>())

    init {
        runBlocking {
            idStudent.value =
                studentRepo.getStudentByEmail(userDataStore.getEmail())?.idStudent ?: 0

            studentExams.value =
                if (idStudent.value == 0) emptyList()
                else studentExamsRepo.getExamsByStudentId(idStudent.value).first()

            allExams.value = examRepo.getExams().first()
        }
    }

    fun findStudentExams(): List<StudentExam> {
        return studentExams.value
    }

    fun examsInYear(year: Int): List<StudentExam> {
        val idExams = allExams.value
            .filter { e -> e.course.yearOfTeaching == year }
            .map { e -> e.idExam }

        return studentExams.value.filter { s -> s.idExam in idExams }
    }

    fun bookedExamsPerYear(year: Int): List<StudentExam> {
        val idExams = allExams.value
            .filter { e -> e.course.yearOfTeaching == year }
            .map { e -> e.idExam }

        return studentExams.value.filter { exam -> exam.booked && exam.idExam in idExams }
    }

    fun takenExamsPerYear(year: Int): List<StudentExam> {
        return studentExams.value.filter { exam -> exam.grade > 0 }
    }

    fun examsToTakePerYear(year: Int): List<Exam> {
        val idTakenExams = studentExams.value
            .filter { exam -> exam.grade > 0 }
            .map { exam -> exam.idExam }

        return allExams.value.filter { exam ->
            exam.course.yearOfTeaching == year && exam.idExam !in idTakenExams
        }
    }

    fun examsToTake(): List<Exam> {
        val idTakenExams = studentExams.value
            .filter { exam -> exam.grade > 0 }
            .map { exam -> exam.idExam }

        return allExams.value
            .filter { exam -> exam.idExam !in idTakenExams }
            .distinctBy { exam -> exam.course.name }
    }

    fun bookableExamsPerYear(year: Int): List<Exam> {
        // bookable exams must not have a grade
        val idBookedExams = studentExams.value
            .filter { exam -> exam.booked }
            .map { e -> e.idExam }

        return allExams.value
            .filter { exam -> exam.course.yearOfTeaching == year && exam.idExam !in idBookedExams }
            .distinctBy { exam -> exam.course.name }
    }

    fun examNameById(idExam: Int): String {
        return allExams.value
            .filter { e -> e.idExam == idExam }
            .map { e -> e.course.name }
            .first()
    }

    fun addExam(studentExam: StudentExam) {
        viewModelScope.launch {
            studentExamsRepo.addExam(studentExam)
        }
    }

    fun removeExam(studentExam: StudentExam) {
        viewModelScope.launch {
            studentExamsRepo.removeExam(studentExam)
        }
    }
}
