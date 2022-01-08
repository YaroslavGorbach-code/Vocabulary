package yaroslavgorbach.koropapps.vocabulary.business.settings

import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ChangeAdFeatureAvailability(private val repoSettings: RepoSettings) {
    suspend operator fun invoke(isAvailable: Boolean) =
        repoSettings.changeAddFeatureAvailability(isAvailable)
}