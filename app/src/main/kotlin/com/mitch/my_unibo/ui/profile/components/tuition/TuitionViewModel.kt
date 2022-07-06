package com.mitch.my_unibo.ui.profile.components.tuition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.student.StudentRepo
import com.mitch.my_unibo.features.tuition.StudentTuition
import com.mitch.my_unibo.features.tuition.StudentTuitionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@HiltViewModel
class TuitionViewModel @Inject constructor(
    private val studentTuitionRepo: StudentTuitionRepo,
    private val studentRepo: StudentRepo,
    private val userDataStore: UserDataStore
) : ViewModel() {

    private val idStudent = MutableStateFlow(0)
    private var studentTuition by mutableStateOf(emptyList<StudentTuition>())

    init {
        runBlocking {
            idStudent.value =
                studentRepo.getStudentByEmail(userDataStore.getEmail())?.idStudent ?: 0

            if (idStudent.value != 0) {
                studentTuition = studentTuitionRepo.getStudentTuition(idStudent.value).first()
            }
        }
    }

    fun findTuition(): List<StudentTuition> {
        return studentTuition
    }

    fun tuitionInYear(year: Int): List<StudentTuition> {
        return studentTuition.filter { t -> t.tuition.year == year }
    }
}
