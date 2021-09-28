package yaroslavgorbach.koropapps.vocabulary.data.settings.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.SettingsDataStore
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

class RepoSettingsImp(private val localDataStore: SettingsDataStore) : RepoSettings {

    override fun observeThemes(context: Context): Flow<List<Theme>> {
        return localDataStore.observeThemes(context)
    }

    override fun observeCurrentTheme(context: Context): Flow<Theme> {
        return localDataStore.observeCurrentTheme(context)
    }

    override suspend fun changeTheme(context: Context, theme: Theme) {
        localDataStore.changeTheme(context, theme)
    }

    override fun observeUiMode(context: Context): Flow<UiMode> {
        return localDataStore.observeUiMode(context)
    }

    override suspend fun changeUiMode(context: Context, uiMode: UiMode) {
        localDataStore.changeUiMode(context, uiMode)
    }

    override fun observeNotification(context: Context): Flow<Notification> {
        return localDataStore.observeNotification(context)
    }

    override suspend fun updateNotification(context: Context, notification: Notification) {
        return localDataStore.updateNotification(context, notification)
    }
}