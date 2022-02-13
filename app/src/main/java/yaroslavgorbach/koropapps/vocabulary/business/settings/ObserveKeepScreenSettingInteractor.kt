package yaroslavgorbach.koropapps.vocabulary.business.settings

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveKeepScreenSettingInteractor(
    private val repoSettings: RepoSettings,
) {
    operator fun invoke() = repoSettings.observeKeepScreenOnState()

}