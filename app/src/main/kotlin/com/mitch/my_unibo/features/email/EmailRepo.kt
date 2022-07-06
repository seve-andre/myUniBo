package com.mitch.my_unibo.features.email

import kotlinx.coroutines.flow.Flow

interface EmailRepo {
    fun getInbox(senderEmail: String): Flow<List<Email>>
//    fun getSentEmails(): Flow<List<Email>>

    suspend fun removeEmail(idEmail: Int)
}
