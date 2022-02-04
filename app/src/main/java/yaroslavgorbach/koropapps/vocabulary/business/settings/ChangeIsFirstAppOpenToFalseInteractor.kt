package yaroslavgorbach.koropapps.vocabulary.business.settings

import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ChangeIsFirstAppOpenToFalseInteractor(private val repoSettings: RepoSettings) {
    suspend operator fun invoke() {
        repoSettings.changeIsFirsAppOpen(false)
    }
}