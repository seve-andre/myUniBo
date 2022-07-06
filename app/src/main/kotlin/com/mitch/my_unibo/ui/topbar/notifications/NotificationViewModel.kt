package com.mitch.my_unibo.ui.topbar.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.features.notifications.Notification
import com.mitch.my_unibo.features.notifications.NotificationRepo
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.features.student.StudentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepo: NotificationRepo,
    private val studentRepo: StudentRepo,
    private val userDataStore: UserDataStore
) : ViewModel() {
    private var student: Student? = null
    private val notifications = MutableStateFlow(emptyList<Notification>())

    init {
        runBlocking {
            student = studentRepo.getStudentByEmail(userDataStore.getEmail())
        }
    }

    fun notifications(): List<Notification> {
        runBlocking {
            notifications.value = notificationRepo.getNotifications(student!!.idStudent!!).first()
        }

        return notifications.value
    }

    fun deleteNotification(idNotification: Int) {
        viewModelScope.launch {
            notificationRepo.deleteNotification(idNotification)
        }
    }
}
