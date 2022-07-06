package com.mitch.my_unibo.features.email

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "emails")
data class Email(
    @PrimaryKey(autoGenerate = true) val idEmail: Int? = null,
    val sentDateTime: LocalDateTime,
    val senderName: String,
    val senderEmail: String,
    val toRecipients: List<Receiver>,
    val subject: String,
    val bodyPreview: String,
    val body: String
)
