package com.example.tilek_shambetaliev_hw4.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.example.tilek_shambetaliev_hw4.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {

       val channel = NotificationChannel(
            CHANNEL_ID,
            "Heads up Notification",
        NotificationManager.IMPORTANCE_HIGH
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification = android.app.Notification.Builder(this, CHANNEL_ID)
        notification.apply {
            setContentTitle(message.notification?.title)
            setContentText(message.notification?.body)
            setSmallIcon(R.drawable.ic_google)
            setAutoCancel(true)
        }
        NotificationManagerCompat.from(this).notify(1, notification.build())

        super.onMessageReceived(message)
    }

    companion object {
        const val CHANNEL_ID = "channel.taskApp"
    }
}