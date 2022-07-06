package com.mitch.my_unibo.features.notifications

import kotlinx.coroutines.flow.Flow

interface NotificationRepo {

    fun getNotifications(idStudent: Int): Flow<List<Notification>>

    suspend fun deleteNotification(idNotification: Int)
}
