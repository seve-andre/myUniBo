package com.mitch.my_unibo.features.student

interface StudentRepo {
    enum class LoginResult {
        CORRECT,
        WRONG
    }

    suspend fun getStudentById(idStudent: Int): Student?
    suspend fun getStudentByEmail(email: String): Student?
    suspend fun addStudent(student: Student)
    suspend fun checkLogin(email: String, password: String): LoginResult
}
