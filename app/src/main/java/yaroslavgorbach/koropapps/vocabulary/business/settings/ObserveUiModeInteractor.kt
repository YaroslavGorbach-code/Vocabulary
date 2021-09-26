package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ObserveUiModeInteractor(private val repoSettings: RepoSettings) {

    operator fun invoke(context: Context): Flow<UiMode> {
        return repoSettings.observeUiMode(context)
    }
}