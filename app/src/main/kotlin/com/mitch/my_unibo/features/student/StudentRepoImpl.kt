package com.mitch.my_unibo.features.student

class StudentRepoImpl(
    private val dao: StudentDao
): StudentRepo {

    override suspend fun getStudentById(idStudent: Int): Student? {
        return dao.getStudentById(idStudent)
    }

    override suspend fun getStudentByEmail(email: String): Student? {
        return dao.getStudentByEmail(email)
    }

    override suspend fun addStudent(student: Student) {
        dao.addStudent(student)
    }

    override suspend fun checkLogin(email: String, password: String): StudentRepo.LoginResult {
        // if email does not exits, return WRONG immediately
        val student = dao.getStudentByEmail(email) ?: return StudentRepo.LoginResult.WRONG

        // check if password associated to email is correct
        if (student.password != password) {
            return StudentRepo.LoginResult.WRONG
        }

        return StudentRepo.LoginResult.CORRECT
    }
}
