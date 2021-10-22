package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveNotificationInteractor(private val repoSettings: RepoSettings) {

    operator fun invoke(): Flow<Notification> {
        return repoSettings.observeNotification()
    }
}