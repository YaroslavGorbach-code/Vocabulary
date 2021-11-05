package yaroslavgorbach.koropapps.vocabulary.business.settings

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveIsFirstAppOpenInteractor(
    private val repoSettings: RepoSettings,
) {
    operator fun invoke(): Flow<Boolean> {
        return repoSettings.observeIsFirstAppOpen()
    }
}