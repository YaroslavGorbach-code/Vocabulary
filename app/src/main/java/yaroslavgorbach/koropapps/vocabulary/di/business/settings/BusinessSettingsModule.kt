package yaroslavgorbach.koropapps.vocabulary.di.business.settings

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.settings.*
import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings
import yaroslavgorbach.koropapps.vocabulary.di.data.settings.DataSettingsModule

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

    @Provides
    fun provideObserveAutoRecordStateInteractor(repoSettings: RepoSettings): ObserveAutoRecordStateInteractor {
        return ObserveAutoRecordStateInteractor(repoSettings)
    }

    @Provides
    fun provideChangeAutoRecordStateInteractor(repoSettings: RepoSettings): ChangeAutoRecordStateInteractor {
        return ChangeAutoRecordStateInteractor(repoSettings)
    }

    @Provides
    fun provideObserveIsFirsAppOpen(
        repoSettings: RepoSettings,
    ): ObserveIsFirstAppOpenInteractor {
        return ObserveIsFirstAppOpenInteractor(repoSettings)
    }

    @Provides
    fun provideChangeIsFalseAppOpenToFalseInteractor(repoSettings: RepoSettings): ChangeIsFirstAppOpenToFalseInteractor {
        return ChangeIsFirstAppOpenToFalseInteractor(repoSettings)
    }

    @Provides
    fun provideChangeAdFeatureAvailability(repoSettings: RepoSettings): ChangeAdFeatureAvailability {
        return ChangeAdFeatureAvailability(repoSettings)
    }

    @Provides
    fun provideObserveAdFeatureAvailability(repoSettings: RepoSettings): ObserveAdFeatureAvailability {
        return ObserveAdFeatureAvailability(repoSettings)
    }

    @Provides
    fun provideObserveInterstitialShowAvailability(repoSettings: RepoSettings): ObserveInterstitialShowAvailability {
        return ObserveInterstitialShowAvailability(repoSettings)
    }

    @Provides
    fun provideObserveKeepScreenOnStateInteractor(repoSettings: RepoSettings): ObserveKeepScreenSettingInteractor {
        return ObserveKeepScreenSettingInteractor(repoSettings)
    }

    @Provides
    fun provideChangeKeepScreenOnInteractor(repoSettings: RepoSettings): ChangeKeepScreenOnInteractor {
        return ChangeKeepScreenOnInteractor(repoSettings)
    }
}
