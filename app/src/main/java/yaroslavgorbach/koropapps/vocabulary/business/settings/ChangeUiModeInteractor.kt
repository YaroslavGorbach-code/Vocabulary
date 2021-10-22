package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ChangeUiModeInteractor(private val repoSettings: RepoSettings) {

    suspend operator fun invoke(uiMode: UiMode){
        repoSettings.changeUiMode(uiMode)
    }
}