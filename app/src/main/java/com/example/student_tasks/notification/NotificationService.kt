package com.example.student_tasks.notification

import com.google.firebase.messaging.FirebaseMessagingService

class NotificationService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}