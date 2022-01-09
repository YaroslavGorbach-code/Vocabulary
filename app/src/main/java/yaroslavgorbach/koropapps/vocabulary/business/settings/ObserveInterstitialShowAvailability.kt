package yaroslavgorbach.koropapps.vocabulary.business.settings

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveInterstitialShowAvailability(private val repoSettings: RepoSettings) {
    suspend operator fun invoke(): Flow<Boolean> {
        repoSettings.incrementInterstatialAdShowCounter()
        return repoSettings.isInterstitialAvailable()
    }
}