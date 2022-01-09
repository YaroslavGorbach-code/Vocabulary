package yaroslavgorbach.koropapps.vocabulary.business.settings

import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveAdFeatureAvailability(private val repoSettings: RepoSettings) {
    operator fun invoke() = repoSettings.isAdFeatureAvailable()
}