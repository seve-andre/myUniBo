package com.mitch.my_unibo.features.notifications

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mitch.my_unibo.features.student.Student
import java.time.LocalDateTime

@Entity(
    tableName = "notifications",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("idStudent"),
            childColumns = arrayOf("idStudent"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Notification(
    @PrimaryKey(autoGenerate = true) val idNotification: Int? = null,
    val idStudent: Int,
    val title: String,
    val description: String,
    val isRead: Boolean,
    val sentDateTime: LocalDateTime
)
