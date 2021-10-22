package yaroslavgorbach.koropapps.vocabulary.di.data.settings

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.App
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.SettingsDataStore
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.SettingsDataStoreImp
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettingsImp
import javax.inject.Singleton

@Module
class DataSettingsModule {

    @Singleton
    @Provides
    fun provideSettingsDataStore(): SettingsDataStore {
        return SettingsDataStoreImp()
    }

    @Singleton
    @Provides
    fun provideRepoSettings(
        settingsDataStore: SettingsDataStore,
        context: Application
    ): RepoSettings {
        return RepoSettingsImp(settingsDataStore, context)
    }

}