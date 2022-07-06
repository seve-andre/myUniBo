package com.mitch.my_unibo.features.authentication

interface Authenticator {

    fun isEmailTaken(
        email: String
    ): Boolean

    fun checkEmailAndPassword(
        email: String,
        password: String
    ): Boolean

    fun loginStudent(email: String)

    fun registerStudent(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    )
}
