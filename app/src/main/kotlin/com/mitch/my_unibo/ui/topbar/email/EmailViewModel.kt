package com.mitch.my_unibo.ui.topbar.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.email.Email
import com.mitch.my_unibo.features.email.EmailRepo
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.features.student.StudentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val emailRepo: EmailRepo,
    private val studentRepo: StudentRepo,
    private val userDataStore: UserDataStore
) : ViewModel() {

    private var student: Student? = null
    private val emails = MutableStateFlow(emptyList<Email>())

    init {
        runBlocking {
            student = studentRepo.getStudentByEmail(userDataStore.getEmail())
        }
    }

    fun inbox(): List<Email> {
        runBlocking {
            emails.value = emailRepo.getInbox(student!!.email).first()
        }

        return emails.value
    }

    fun removeEmail(idEmail: Int) {
        viewModelScope.launch {
            emailRepo.removeEmail(idEmail)
        }
    }
}
