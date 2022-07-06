package com.mitch.my_unibo.features.professor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "professors")
data class Professor(
    @PrimaryKey(autoGenerate = true) val idProfessor: Int? = null,
    val firstName: String,
    val lastName: String,
    val type: Type
) {
    enum class Type {
        ASSOCIATE,
        TUTOR,
        RESEARCHER
    }
}
