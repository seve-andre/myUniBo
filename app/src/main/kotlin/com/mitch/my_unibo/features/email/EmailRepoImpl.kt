package com.mitch.my_unibo.features.email

import kotlinx.coroutines.flow.Flow

class EmailRepoImpl(
    private val dao: EmailDao
) : EmailRepo {

    override fun getInbox(senderEmail: String): Flow<List<Email>> {
        return dao.inbox(senderEmail)
    }

    override suspend fun removeEmail(idEmail: Int) {
        dao.removeEmail(idEmail)
    }

    /*override fun getSentEmails(): Flow<List<Email>> {
        return dao.sentEmails()
    }*/
}
