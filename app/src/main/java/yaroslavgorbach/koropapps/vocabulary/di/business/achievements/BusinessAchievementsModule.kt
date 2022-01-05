package yaroslavgorbach.koropapps.vocabulary.di.business.achievements

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ClearAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ObserveAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.repo.RepoAchievements
import yaroslavgorbach.koropapps.vocabulary.di.data.achievements.DataModuleAchievements

@Module(includes = [DataModuleAchievements::class])
class BusinessAchievementsModule {

    @Provides
    fun provideObserveAchievementsInteractor(repoAchievements: RepoAchievements): ObserveAchievementsInteractor {
        return ObserveAchievementsInteractor(repoAchievements)
    }

    @Provides
    fun provideAchieveAchievementInteractor(repoAchievements: RepoAchievements): AchieveAchievementInteractor {
        return AchieveAchievementInteractor(repoAchievements)
    }

    @Provides
    fun provideClearAchievementsInteractor(repoAchievements: RepoAchievements): ClearAchievementsInteractor {
        return ClearAchievementsInteractor(repoAchievements)
    }
}