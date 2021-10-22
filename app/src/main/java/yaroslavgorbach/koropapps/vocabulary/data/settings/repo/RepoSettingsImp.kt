package yaroslavgorbach.koropapps.vocabulary.data.settings.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.SettingsDataStore
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

class RepoSettingsImp(private val localDataStore: SettingsDataStore, private val context: Context) : RepoSettings {

    override fun observeThemes(): Flow<List<Theme>> {
        return localDataStore.observeThemes(context)
    }

    override fun observeCurrentTheme(): Flow<Theme> {
        return localDataStore.observeCurrentTheme(context)
    }

    override suspend fun changeTheme(theme: Theme) {
        localDataStore.changeTheme(context, theme)
    }

    override fun observeUiMode(): Flow<UiMode> {
        return localDataStore.observeUiMode(context)
    }

    override suspend fun changeUiMode(uiMode: UiMode) {
        localDataStore.changeUiMode(context, uiMode)
    }

    override fun observeNotification(): Flow<Notification> {
        return localDataStore.observeNotification(context)
    }

    override suspend fun updateNotification(notification: Notification) {
        return localDataStore.updateNotification(context, notification)
    }
}