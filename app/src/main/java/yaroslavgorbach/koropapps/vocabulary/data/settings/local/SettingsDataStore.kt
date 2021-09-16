package yaroslavgorbach.koropapps.vocabulary.data.settings.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.data.settings.factory.ThemeFactory
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore {
    companion object {
        private val THEME_RES_KEY = intPreferencesKey("THEME_RES_KEY")
    }

    fun getCurrentTheme(context: Context): Flow<Theme> {
        return context.settingsDataStore.data
            .map { prefs ->
                val themeRes = prefs[THEME_RES_KEY] ?: 0
                ThemeFactory().create(themeRes)
            }
    }

    suspend fun changeTheme(context: Context, theme: Theme) {
        context.settingsDataStore.edit { prefs ->
            prefs[THEME_RES_KEY] = theme.res
        }
    }

}