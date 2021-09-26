package yaroslavgorbach.koropapps.vocabulary.data.settings.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

interface RepoSettings {
    fun observeThemes(context: Context): Flow<List<Theme>>

    fun observeCurrentTheme(context: Context): Flow<Theme>

    suspend fun changeTheme(context: Context, theme: Theme)

    fun observeUiMode(context: Context): Flow<UiMode>

    suspend fun changeUiMode(context: Context, uiMode: UiMode)
}