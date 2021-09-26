package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ChangeUiModeInteractor(private val repoSettings: RepoSettings) {

    suspend operator fun invoke(context: Context, uiMode: UiMode){
        repoSettings.changeUiMode(context, uiMode)
    }
}