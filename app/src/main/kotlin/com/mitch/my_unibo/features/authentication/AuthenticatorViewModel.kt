package com.mitch.my_unibo.features.authentication

import androidx.lifecycle.ViewModel
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.features.student.StudentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

@HiltViewModel
class AuthenticatorViewModel @Inject constructor(
    private val repo: StudentRepo,
    private val userDataStore: UserDataStore
) : Authenticator, ViewModel() {

    override fun isEmailTaken(email: String): Boolean {
        val emailTaken = MutableStateFlow(false)
        runBlocking {
            emailTaken.value = repo.getStudentByEmail(email) != null
        }

        return emailTaken.value
    }

    override fun checkEmailAndPassword(email: String, password: String): Boolean {
        val isLoginCorrect = MutableStateFlow(false)
        runBlocking {
            isLoginCorrect.value = when (repo.checkLogin(email, password)) {
                StudentRepo.LoginResult.CORRECT -> true
                StudentRepo.LoginResult.WRONG -> false
            }
        }

        return isLoginCorrect.value
    }

    override fun loginStudent(email: String) {
        runBlocking {
            userDataStore.login()
            userDataStore.saveEmail(email)
        }
    }

    override fun registerStudent(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        runBlocking {
            repo.addStudent(
                Student(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
            )
        }
    }
}
