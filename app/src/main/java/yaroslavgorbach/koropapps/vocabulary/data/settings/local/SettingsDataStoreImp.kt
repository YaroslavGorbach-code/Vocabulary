package yaroslavgorbach.koropapps.vocabulary.data.settings.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory.ThemeFactory
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory.UiModeFactory
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.mapper.MapStyleResToThemeRes
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.utils.isSystemNightMode

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStoreImp: SettingsDataStore {

    companion object {
        private val THEME_RES_KEY = intPreferencesKey("THEME_RES_KEY")
        private val IS_DARK_UI_MODE_KEY = booleanPreferencesKey("IS_DARK_UI_MODE_KEY")
        private const val DEFAULT_THEME_RES = R.style.BaseAppTheme_Teal
    }

    private var themes: List<Theme> = listOf(
        Theme.Teal(),
        Theme.Blue(),
        Theme.Indigo(),
        Theme.DeepPurple(),
        Theme.Purple(),
        Theme.Pink(),
        Theme.Red(),
        Theme.LightBlue(),
        Theme.Green(),
        Theme.LightGreen(),
        Theme.Lime(),
        Theme.Yellow(),
        Theme.Amber(),
        Theme.Orange(),
        Theme.DeepOrange(),
        Theme.Brown(),
    )

    override fun observeThemes(context: Context): Flow<List<Theme>> {
        return observeCurrentTheme(context).map { theme ->
            themes.apply {
                themes.forEach { it.isCurrent = false }
                requireNotNull(themes.find { it.colorPrimary == theme.colorPrimary }).isCurrent = true
            }
        }
    }

    override fun observeCurrentTheme(context: Context): Flow<Theme> {
        return context.settingsDataStore.data
            .map { prefs ->
                val styleRes = prefs[THEME_RES_KEY] ?: DEFAULT_THEME_RES
                ThemeFactory().create(MapStyleResToThemeRes().map(styleRes))
            }
    }

    override suspend fun changeTheme(context: Context, theme: Theme) {
        context.settingsDataStore.edit { prefs ->
            prefs[THEME_RES_KEY] = theme.res.id
        }
    }

    override fun observeUiMode(context: Context): Flow<UiMode> {
        return context.settingsDataStore.data
            .map { prefs ->
                val isDark = prefs[IS_DARK_UI_MODE_KEY] ?: context.isSystemNightMode()
                UiModeFactory().create(isDark)
            }
    }

    override suspend fun changeUiMode(context: Context, uiMode: UiMode) {
        context.settingsDataStore.edit { prefs ->
            when (uiMode) {
                is UiMode.Dark -> prefs[IS_DARK_UI_MODE_KEY] = true
                is UiMode.Light -> prefs[IS_DARK_UI_MODE_KEY] = false
            }
        }
    }
}