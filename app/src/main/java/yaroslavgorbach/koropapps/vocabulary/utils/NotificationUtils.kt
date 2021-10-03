package yaroslavgorbach.koropapps.vocabulary.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.MainActivity
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.feature.common.workers.PeriodicNotificationBroadcast

const val CHANNEL_ID = "1"
const val CHANNEL_NAME = "Notifications"

const val NOTIFICATION_ID = 1

const val NOTIFICATION_TEXT_KEY = "NOTIFICATION_TEXT_KEY"

fun NotificationManager.createChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createNotificationChannel(
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        )
    }
}

@SuppressLint("UnspecifiedImmutableFlag")
@FlowPreview
@InternalCoroutinesApi
fun Context.showNotification(text: String) {

    val currentIntent = Intent(this, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    }

    val contentPendingIntent = PendingIntent.getActivity(
        this,
        NOTIFICATION_ID,
        currentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
        setContentTitle(getString(R.string.app_name))
        setContentText(text)
        setContentIntent(contentPendingIntent)
        setSmallIcon(R.drawable.ic_rocket)
        setAutoCancel(true)
        priority = NotificationCompat.PRIORITY_HIGH
    }

    NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
}

@SuppressLint("UnspecifiedImmutableFlag")
@InternalCoroutinesApi
fun Context.scheduleNotification(notification: Notification) {
    if (notification.isActive.not()) {
        cancelNotification()
        return
    }

    val notifyIntent = Intent(this, PeriodicNotificationBroadcast::class.java).apply {
        putExtra(NOTIFICATION_TEXT_KEY, notification.text)
    }

    val notifyPendingIntent = PendingIntent.getBroadcast(
        this,
        0,
        notifyIntent,
        PendingIntent.FLAG_UPDATE_CURRENT,
    )

    (getSystemService(Context.ALARM_SERVICE) as AlarmManager).setRepeating(
        AlarmManager.RTC_WAKEUP,
        notification.scheduleTime,
        AlarmManager.INTERVAL_DAY,
        notifyPendingIntent
    )
}

fun Context.cancelNotification() {
    NotificationManagerCompat.from(this).cancelAll()
}