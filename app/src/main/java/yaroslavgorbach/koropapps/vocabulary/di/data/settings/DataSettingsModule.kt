package yaroslavgorbach.koropapps.vocabulary.di.data.settings

import dagger.Module
import dagger.Provides
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
    fun provideRepoSettings(settingsDataStore: SettingsDataStore): RepoSettings {
        return RepoSettingsImp(settingsDataStore)
    }

}