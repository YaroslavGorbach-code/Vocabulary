package yaroslavgorbach.koropapps.vocabulary.business.settings

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ChangeThemeInteractor(private val repoSettings: RepoSettings) {

    suspend operator fun invoke(theme: Theme) {
        repoSettings.changeTheme(theme)
    }
}