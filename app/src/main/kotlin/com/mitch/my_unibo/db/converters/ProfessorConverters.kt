package com.mitch.my_unibo.db.converters

import androidx.room.TypeConverter
import com.mitch.my_unibo.features.professor.Professor

class ProfessorConverters {

    @TypeConverter
    fun convertProfessorType(professorType: Professor.Type): String = professorType.name

    @TypeConverter
    fun convertToProfessorType(professorTypeString: String): Professor.Type =
        enumValueOf(professorTypeString)
}
