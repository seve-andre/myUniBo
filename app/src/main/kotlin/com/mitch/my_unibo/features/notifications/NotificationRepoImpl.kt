package com.mitch.my_unibo.features.notifications

import kotlinx.coroutines.flow.Flow

class NotificationRepoImpl(
    private val dao: NotificationDao
) : NotificationRepo {

    override fun getNotifications(idStudent: Int): Flow<List<Notification>> {
        return dao.getNotifications(idStudent)
    }

    override suspend fun deleteNotification(idNotification: Int) {
        dao.deleteNotification(idNotification)
    }
}
