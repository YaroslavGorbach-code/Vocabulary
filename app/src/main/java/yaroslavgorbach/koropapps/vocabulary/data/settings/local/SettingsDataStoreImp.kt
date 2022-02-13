package yaroslavgorbach.koropapps.vocabulary.data.settings.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory.NotificationFactory
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory.ThemeFactory
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory.UiModeFactory
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.mapper.MapStyleResToThemeRes
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.getNotificationDefaultText
import yaroslavgorbach.koropapps.vocabulary.utils.isSystemNightMode

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStoreImp : SettingsDataStore {

    companion object {
        private const val DEFAULT_THEME_RES = R.style.BaseAppTheme_Teal
        private const val DEFAULT_NOTIFICATION_HOUR = 12
        private const val DEFAULT_NOTIFICATION_MINUTE = 30
        private const val DEFAULT_NOTIFICATION_IS_ACTIVE = false
        private const val INTERSTITIAL_ADD_SHOW_LIMIT = 2

        private val AUTO_RECORD_STATE_KEY = booleanPreferencesKey("AUTO_RECORD_STATE")

        private val IS_FIRST_APP_OPEN_KEY = booleanPreferencesKey("IS_FIRST_APP_OPEN_KEY")

        private val KEEP_SCREEN_ON_KEY = booleanPreferencesKey("KEEP_SCREEN_ON_KEY")

        private val IS_ADD_FEATURE_AVAILABLE_KEY =
            booleanPreferencesKey("IS_ADD_FEATURE_AVAILABLE_KEY")
        private val INTERSTITIAL_AD_SHOW_NUMBER_KEY =
            intPreferencesKey("IS_INTERSTITIAL_AVAILABLE_KEY")

        private val THEME_RES_KEY = intPreferencesKey("THEME_RES_KEY")
        private val IS_DARK_UI_MODE_KEY = booleanPreferencesKey("IS_DARK_UI_MODE_KEY")

        private val NOTIFICATION_HOUR_KEY = intPreferencesKey("NOTIFICATION_HOUR_KEY")
        private val NOTIFICATION_MINUTE_KEY = intPreferencesKey("NOTIFICATION_MINUTE_KEY")
        private val NOTIFICATION_IS_ACTIVE_KEY = booleanPreferencesKey("NOTIFICATION_IS_ACTIVE_KEY")
        private val NOTIFICATION_TEXT_KEY = stringPreferencesKey("NOTIFICATION_TEXT_KEY")
    }

    private var themes: List<Theme> = listOf(
        Theme.Red(),
        Theme.Pink(),
        Theme.Purple(),
        Theme.DeepPurple(),
        Theme.Indigo(),
        Theme.Blue(),
        Theme.LightBlue(),
        Theme.Teal(),
        Theme.Green(),
        Theme.LightGreen(),
        Theme.Lime(),
        Theme.Yellow(),
        Theme.Amber(),
        Theme.Orange(),
        Theme.DeepOrange(),
        Theme.Brown(),
        Theme.BlueGray(),
    )

    override fun observeAutoRecordState(context: Context): Flow<Boolean> {
        return context.settingsDataStore.data
            .map { prefs ->
                prefs[AUTO_RECORD_STATE_KEY] ?: false
            }
    }

    override fun observeIsFirstAppOpen(context: Context): Flow<Boolean> {
        return context.settingsDataStore.data
            .map { prefs ->
                prefs[IS_FIRST_APP_OPEN_KEY] ?: true
            }
    }

    override fun isAdFeatureAvailable(context: Context): Flow<Boolean> {
        return context.settingsDataStore.data
            .map { prefs -> prefs[IS_ADD_FEATURE_AVAILABLE_KEY] ?: true }
    }

    override suspend fun changeAddFeatureAvailability(context: Context, isAvailable: Boolean) {
        context.settingsDataStore.edit { prefs ->
            prefs[IS_ADD_FEATURE_AVAILABLE_KEY] = isAvailable
        }
    }

    override fun isInterstitialAvailable(context: Context): Flow<Boolean> {
        return context.settingsDataStore.data
            .map { prefs ->
                prefs[INTERSTITIAL_AD_SHOW_NUMBER_KEY]?.rem(INTERSTITIAL_ADD_SHOW_LIMIT) == 0
            }
    }

    override suspend fun incrementInterstitialAdShowCounter(context: Context) {
        context.settingsDataStore.edit { prefs ->
            prefs[INTERSTITIAL_AD_SHOW_NUMBER_KEY] =
                prefs[INTERSTITIAL_AD_SHOW_NUMBER_KEY]?.inc() ?: 0
        }
    }

    override suspend fun changeAutoRecordState(context: Context, isAutoRecordSwitchedOn: Boolean) {
        context.settingsDataStore.edit { prefs ->
            prefs[AUTO_RECORD_STATE_KEY] = isAutoRecordSwitchedOn
        }
    }

    override suspend fun changeKeepSeenOn(context: Context, isNeedToKeepScreenOn: Boolean) {
        context.settingsDataStore.edit { prefs ->
            prefs[KEEP_SCREEN_ON_KEY] = isNeedToKeepScreenOn
        }
    }

    override fun observeKeepScreenOnState(context: Context): Flow<Boolean> {
        return context.settingsDataStore.data
            .map { prefs ->
                prefs[KEEP_SCREEN_ON_KEY] ?: true
            }
    }

    override suspend fun changeIsFirstAppOpen(context: Context, isFirstAppOpen: Boolean) {
        context.settingsDataStore.edit { prefs ->
            prefs[IS_FIRST_APP_OPEN_KEY] = isFirstAppOpen
        }
    }

    override fun observeThemes(context: Context): Flow<List<Theme>> {
        return observeCurrentTheme(context).map { theme ->
            themes.apply {
                themes.forEach { it.isCurrent = false }
                requireNotNull(themes.find { it.colorPrimaryRes == theme.colorPrimaryRes }).isCurrent =
                    true
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

    override fun observeNotification(context: Context): Flow<Notification> {
        return context.settingsDataStore.data
            .map { prefs ->
                val isActive: Boolean =
                    prefs[NOTIFICATION_IS_ACTIVE_KEY] ?: DEFAULT_NOTIFICATION_IS_ACTIVE
                val hour: Int = prefs[NOTIFICATION_HOUR_KEY] ?: DEFAULT_NOTIFICATION_HOUR
                val minute: Int = prefs[NOTIFICATION_MINUTE_KEY] ?: DEFAULT_NOTIFICATION_MINUTE
                val text: String = prefs[NOTIFICATION_TEXT_KEY] ?: getNotificationDefaultText()

                NotificationFactory().create(
                    isActive = isActive,
                    hour = hour,
                    minute = minute,
                    text = text,
                )
            }
    }

    override suspend fun updateNotification(context: Context, notification: Notification) {
        context.settingsDataStore.edit { prefs ->
            prefs[NOTIFICATION_HOUR_KEY] = notification.hour
            prefs[NOTIFICATION_MINUTE_KEY] = notification.minute
            prefs[NOTIFICATION_TEXT_KEY] = notification.text
            prefs[NOTIFICATION_IS_ACTIVE_KEY] = notification.isActive
        }
    }
}