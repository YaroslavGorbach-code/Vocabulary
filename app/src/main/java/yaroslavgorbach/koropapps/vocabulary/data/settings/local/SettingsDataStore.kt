package yaroslavgorbach.koropapps.vocabulary.data.settings.local

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

interface SettingsDataStore {
    fun observeThemes(context: Context): Flow<List<Theme>>

    fun observeCurrentTheme(context: Context): Flow<Theme>

    suspend fun changeAutoRecordState(context: Context, isAutoRecordSwitchedOn: Boolean)

    suspend fun changeIsFirstAppOpen(context: Context, isFirstAppOpen: Boolean)

    suspend fun changeTheme(context: Context, theme: Theme)

    fun observeUiMode(context: Context): Flow<UiMode>

    suspend fun changeUiMode(context: Context, uiMode: UiMode)

    fun observeNotification(context: Context): Flow<Notification>

    suspend fun updateNotification(context: Context, notification: Notification)

    fun observeAutoRecordState(context: Context): Flow<Boolean>

    fun observeIsFirstAppOpen(context: Context): Flow<Boolean>

    fun isAdFeatureAvailable(context: Context): Flow<Boolean>

    suspend fun changeAddFeatureAvailability(context: Context, isAvailable: Boolean)

    fun isInterstitialAvailable(context: Context): Flow<Boolean>

    suspend fun incrementInterstitialAdShowCounter(context: Context)

}