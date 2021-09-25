package yaroslavgorbach.koropapps.vocabulary.data.settings.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme

interface RepoSettings {
    fun observeThemes(context: Context): Flow<List<Theme>>

    fun observeCurrentTheme(context: Context): Flow<Theme>

    suspend fun changeTheme(context: Context, theme: Theme)
}