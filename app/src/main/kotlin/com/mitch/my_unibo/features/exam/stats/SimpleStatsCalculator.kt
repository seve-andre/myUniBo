package com.mitch.my_unibo.features.exam.stats

import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.course.Course
import com.mitch.my_unibo.features.course.CourseRepo
import com.mitch.my_unibo.features.exam.Exam
import com.mitch.my_unibo.features.exam.ExamRepo
import com.mitch.my_unibo.features.exam.student.StudentExam
import com.mitch.my_unibo.features.exam.student.StudentExamRepo
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.features.student.StudentRepo
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SimpleStatsCalculator @Inject constructor(
    private val studentRepo: StudentRepo,
    private val studentExamRepo: StudentExamRepo,
    private val examRepo: ExamRepo,
    private val courseRepo: CourseRepo,
    private val userDataStore: UserDataStore
) : StatsCalculator {

    private var student: Student? = null
    private var takenExams: List<StudentExam> = mutableListOf()
    private var allExams: List<Exam> = mutableListOf()
    private var allCourses: List<Course> = mutableListOf()

    init {
        runBlocking {
            student = studentRepo.getStudentByEmail(userDataStore.getEmail())
            takenExams = studentExamRepo.getExamsByStudentId(student!!.idStudent!!).first()
                .filter { exam -> exam.grade > 0 }
            allExams = examRepo.getExams().first()
            allCourses = courseRepo.getCourses().first()
        }
    }

    override fun nTakenExams(): Int {
        return takenExams.size
    }

    override fun nTotalExams(): Int {
        return allCourses.size
    }

    override fun totalCfu(): Int {
        return allCourses.sumOf { course -> course.cfu }
    }

    override fun takenCfu(): Int {
        val takenExamsIds = takenExams.map { exam -> exam.idExam }

        return allExams
            .filter { exam -> exam.idExam in takenExamsIds }
            .sumOf { exam -> exam.course.cfu }
    }

    override fun gpa(): Double {
        val average = takenExams.sumOf { exam ->
            runBlocking { examRepo.getCfuFromExam(exam.idExam) * exam.grade }
        }

        return if (takenCfu() == 0) 0.0 else (average / takenCfu()).toDouble()
    }

    override fun gpaFromExams(exams: List<StudentExam>): Double {
        val sumGrades = exams.sumOf { exam ->
            runBlocking { examRepo.getCfuFromExam(exam.idExam) * exam.grade }
        }
        val totalCfu = exams.sumOf { exam -> runBlocking { examRepo.getCfuFromExam(exam.idExam) } }

        return if (totalCfu == 0) 0.0 else sumGrades.toDouble() / totalCfu.toDouble()
    }
}
