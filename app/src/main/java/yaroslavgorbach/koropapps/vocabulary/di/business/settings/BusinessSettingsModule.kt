package yaroslavgorbach.koropapps.vocabulary.di.business.settings

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.settings.ChangeThemeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveCurrentThemeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveThemesInteractor
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings
import yaroslavgorbach.koropapps.vocabulary.di.data.settings.DataSettingsModule
import javax.inject.Singleton

@Module(includes = [DataSettingsModule::class])
class BusinessSettingsModule {

    @Singleton
    @Provides
    fun provideObserveCurrentThemeInteractor(repoSettings: RepoSettings): ObserveCurrentThemeInteractor {
        return ObserveCurrentThemeInteractor(repoSettings)
    }

    @Singleton
    @Provides
    fun provideObserveThemesInteractor(repoSettings: RepoSettings): ObserveThemesInteractor {
        return ObserveThemesInteractor(repoSettings)
    }

    @Singleton
    @Provides
    fun provideChangeThemeInteractor(repoSettings: RepoSettings): ChangeThemeInteractor {
        return ChangeThemeInteractor(repoSettings)
    }

}
