package com.mitch.my_unibo.ui.profile.components

import com.mitch.my_unibo.extensions.length

class StudentIdFormatter {

    companion object {
        fun formatId(idStudent: Int): String {
            val length = idStudent.length()

            return "0".repeat(10 - length) + idStudent.toString()
        }
    }
}
