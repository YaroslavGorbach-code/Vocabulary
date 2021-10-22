package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class UpdateNotificationInteractor(private val repoSettings: RepoSettings) {

    suspend operator fun invoke(notification: Notification) {
        repoSettings.updateNotification(notification)
    }
}