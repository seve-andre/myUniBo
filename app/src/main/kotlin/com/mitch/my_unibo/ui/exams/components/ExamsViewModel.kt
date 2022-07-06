package com.mitch.my_unibo.ui.exams.components

import androidx.lifecycle.ViewModel
import com.mitch.my_unibo.features.exam.Exam
import com.mitch.my_unibo.features.exam.ExamRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@HiltViewModel
class ExamsViewModel @Inject constructor(
    private val examsRepo: ExamRepo
) : ViewModel() {

    private var exams = MutableStateFlow(emptyList<Exam>())

    init {
        runBlocking {
            exams.value = examsRepo.getExams().first()
        }
    }

    fun findById(idExam: Int): Exam? {
        return exams.value.firstOrNull { exam -> exam.idExam == idExam }
    }
}
