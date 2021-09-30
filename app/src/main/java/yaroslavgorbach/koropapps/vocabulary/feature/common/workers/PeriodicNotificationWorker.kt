package yaroslavgorbach.koropapps.vocabulary.feature.common.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import yaroslavgorbach.koropapps.vocabulary.utils.NOTIFICATION_TEXT_KEY
import yaroslavgorbach.koropapps.vocabulary.utils.showNotification

class PeriodicNotificationWorker(
    private val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {

    override fun doWork(): Result {

        val text = inputData.getString(NOTIFICATION_TEXT_KEY) ?: return Result.failure()

        context.showNotification(text)

        return Result.success()
    }
}