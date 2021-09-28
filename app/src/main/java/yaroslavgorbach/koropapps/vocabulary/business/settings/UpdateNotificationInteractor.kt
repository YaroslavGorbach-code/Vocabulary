package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class UpdateNotificationInteractor(private val repoSettings: RepoSettings) {

    suspend operator fun invoke(context: Context, notification: Notification) {
        repoSettings.updateNotification(context, notification)
    }
}