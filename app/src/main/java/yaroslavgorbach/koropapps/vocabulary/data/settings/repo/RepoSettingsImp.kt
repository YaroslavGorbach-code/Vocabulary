package yaroslavgorbach.koropapps.vocabulary.data.settings.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.SettingsDataStore
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

class RepoSettingsImp(private val localDataStore: SettingsDataStore, private val context: Context) :
    RepoSettings {

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

    override suspend fun changeAutoRecordState(isAutoRecordTurnedOn: Boolean) {
        localDataStore.changeAutoRecordState(context, isAutoRecordTurnedOn)
    }

    override fun observeAutoRecordState(): Flow<Boolean> {
        return localDataStore.observeAutoRecordState(context)
    }

    override fun observeIsFirstAppOpen(): Flow<Boolean> {
        return localDataStore.observeIsFirstAppOpen(context)
    }

    override suspend fun changeIsFirsAppOpen(isFirstOpen: Boolean) {
        localDataStore.changeIsFirstAppOpen(context, isFirstOpen)
    }

    override fun isAdFeatureAvailable(): Flow<Boolean> {
        return localDataStore.isAdFeatureAvailable(context)
    }

    override suspend fun changeAddFeatureAvailability(isAvailable: Boolean) {
        return localDataStore.changeAddFeatureAvailability(context, isAvailable)
    }

    override fun isInterstitialAvailable(): Flow<Boolean> {
       return localDataStore.isInterstitialAvailable(context)
    }

    override suspend fun incrementInterstatialAdShowCounter() {
        localDataStore.incrementInterstitialAdShowCounter(context)
    }

    override suspend fun changeKeepSeenOn(isNeedToKeepScreenOn: Boolean) {
        localDataStore.changeKeepSeenOn(context, isNeedToKeepScreenOn)
    }

    override fun observeKeepScreenOnState(): Flow<Boolean> {
        return localDataStore.observeKeepScreenOnState(context)
    }
}