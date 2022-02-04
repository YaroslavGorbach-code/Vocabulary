package yaroslavgorbach.koropapps.vocabulary.business.settings

import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ChangeKeepScreenOnInteractor(private val repoSettings: RepoSettings) {
    suspend operator fun invoke(isNeedToKeepScreenOn: Boolean) {
        repoSettings.changeKeepSeenOn(isNeedToKeepScreenOn)
    }
}