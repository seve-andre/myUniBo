package com.mitch.my_unibo.features.notifications

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notifications WHERE idStudent = :idStudent")
    fun getNotifications(idStudent: Int): Flow<List<Notification>>

    @Query("DELETE FROM notifications WHERE idNotification = :idNotification")
    suspend fun deleteNotification(idNotification: Int)
}
