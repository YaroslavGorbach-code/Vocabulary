package yaroslavgorbach.koropapps.vocabulary.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.feature.common.workers.PeriodicNotificationWorker
import java.util.concurrent.TimeUnit
import kotlin.random.Random

const val CHANNEL_ID = "1"
const val CHANNEL_NAME = "Notifications"

const val NOTIFICATION_ID = 1

const val NOTIFICATION_WORK_TAG = "NOTIFICATION_WORK_TAG"
const val NOTIFICATION_WORK_NAME = "NOTIFICATION_WORK_NAME"

const val NOTIFICATION_TEXT_KEY = "NOTIFICATION_TEXT_KEY"

fun NotificationManager.createChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createNotificationChannel(
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        )
    }
}

fun Context.showNotification(text: String) {
    val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
        setContentTitle(getString(R.string.app_name))
        setContentText(text)
        setSmallIcon(R.drawable.ic_rocket)
        priority = NotificationCompat.PRIORITY_DEFAULT
    }

    NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
}

fun Context.scheduleNotification(notification: Notification) {
    Log.i("dsa", notification.scheduledTime.toString())
    if (notification.isActive.not()) {
        cancelNotification()
        return
    }

    val periodicWork = PeriodicWorkRequestBuilder<PeriodicNotificationWorker>(24, TimeUnit.HOURS)
        .setInitialDelay(
            notification.scheduledTime,
            TimeUnit.MILLISECONDS
        )
        .setInputData(
            workDataOf(
                NOTIFICATION_TEXT_KEY to notification.text
            )
        )

        .addTag(NOTIFICATION_WORK_TAG)
        .build()

    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork(
            NOTIFICATION_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWork
        )
}

fun Context.cancelNotification() {
    NotificationManagerCompat.from(this).cancelAll()
}