package yaroslavgorbach.koropapps.vocabulary.data.settings.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.SettingsDataStore
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme

class RepoSettingsImp(private val localDataStore: SettingsDataStore) : RepoSettings {

    override val themes: List<Theme>
        get() = listOf(
            Theme.Base(),
            Theme.Blue(),
            Theme.Indigo(),
            Theme.DeepPurple(),
            Theme.Purple(),
            Theme.Pink(),
            Theme.Red()
        )

    override fun observeCurrentTheme(context: Context): Flow<Theme> {
        return localDataStore.getCurrentTheme(context)
    }

    override suspend fun changeTheme(context: Context, theme: Theme) {
        localDataStore.changeTheme(context, theme)
    }
}