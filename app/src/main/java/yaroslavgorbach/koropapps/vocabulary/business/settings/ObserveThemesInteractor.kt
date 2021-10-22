package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveThemesInteractor(private val repoSettings: RepoSettings) {

    operator fun invoke(): Flow<List<Theme>> {
        return repoSettings.observeThemes()
    }
}