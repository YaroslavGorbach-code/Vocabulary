package yaroslavgorbach.koropapps.vocabulary.data.settings.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

interface RepoSettings {
    fun observeThemes(): Flow<List<Theme>>

    fun observeCurrentTheme(): Flow<Theme>

    suspend fun changeTheme(theme: Theme)

    fun observeUiMode(): Flow<UiMode>

    suspend fun changeUiMode(uiMode: UiMode)

    fun observeNotification(): Flow<Notification>

    suspend fun updateNotification(notification: Notification)
}