package yaroslavgorbach.koropapps.vocabulary.business.settings

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveCurrentThemeInteractor(private val repoSettings: RepoSettings) {

    operator fun invoke(): Flow<Theme> {
        return repoSettings.observeCurrentTheme()
    }
}