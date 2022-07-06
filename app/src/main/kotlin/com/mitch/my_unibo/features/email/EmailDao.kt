package com.mitch.my_unibo.features.email

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmailDao {
    @Query("SELECT * FROM emails WHERE senderEmail = :senderEmail")
    fun inbox(senderEmail: String): Flow<List<Email>>

    @Query("DELETE FROM emails WHERE idEmail = :idEmail")
    suspend fun removeEmail(idEmail: Int)
}
