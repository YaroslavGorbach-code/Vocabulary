package yaroslavgorbach.koropapps.vocabulary.feature.common.workers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.utils.NOTIFICATION_TEXT_KEY
import yaroslavgorbach.koropapps.vocabulary.utils.showNotification

@InternalCoroutinesApi
class PeriodicNotificationBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val text = intent?.extras?.getString(NOTIFICATION_TEXT_KEY) ?: return

        context?.showNotification(text)
    }
}