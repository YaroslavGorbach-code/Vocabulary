package yaroslavgorbach.koropapps.vocabulary.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

const val CHANNEL_ID = "1"

const val CHANNEL_NAME = "Notifications"

fun NotificationManager.createChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
        )
        createNotificationChannel(notificationChannel)
    }
}