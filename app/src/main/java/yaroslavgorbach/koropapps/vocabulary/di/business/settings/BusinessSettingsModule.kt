package yaroslavgorbach.koropapps.vocabulary.di.business.settings

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.settings.*
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings
import yaroslavgorbach.koropapps.vocabulary.di.data.settings.DataSettingsModule
import javax.inject.Singleton

@Module(includes = [DataSettingsModule::class])
class BusinessSettingsModule {

    @Provides
    fun provideObserveCurrentThemeInteractor(repoSettings: RepoSettings): ObserveCurrentThemeInteractor {
        return ObserveCurrentThemeInteractor(repoSettings)
    }

    @Provides
    fun provideObserveThemesInteractor(repoSettings: RepoSettings): ObserveThemesInteractor {
        return ObserveThemesInteractor(repoSettings)
    }

    @Provides
    fun provideChangeThemeInteractor(repoSettings: RepoSettings): ChangeThemeInteractor {
        return ChangeThemeInteractor(repoSettings)
    }

    @Provides
    fun provideChangeUiModeInteractor(repoSettings: RepoSettings): ChangeUiModeInteractor {
        return ChangeUiModeInteractor(repoSettings)
    }

    @Provides
    fun provideObserveUiModeInteractor(repoSettings: RepoSettings): ObserveUiModeInteractor {
        return ObserveUiModeInteractor(repoSettings)
    }

    @Provides
    fun provideObserveNotificationInteractor(repoSettings: RepoSettings): ObserveNotificationInteractor {
        return ObserveNotificationInteractor(repoSettings)
    }

    @Provides
    fun provideUpdateNotificationInteractor(repoSettings: RepoSettings): UpdateNotificationInteractor {
        return UpdateNotificationInteractor(repoSettings)
    }
}
