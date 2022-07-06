package com.mitch.my_unibo.ui.profile.components

import androidx.lifecycle.ViewModel
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.features.student.StudentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.runBlocking

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentRepo: StudentRepo,
    private val userDataStore: UserDataStore
) : ViewModel() {

    private var student: Student? = null

    init {
        runBlocking {
            student = studentRepo.getStudentByEmail(userDataStore.getEmail())
        }
    }

    fun getLoggedStudent(): Student? {
        return student
    }
}
