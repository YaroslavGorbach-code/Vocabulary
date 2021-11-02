package yaroslavgorbach.koropapps.vocabulary.di.data.achievements

import android.app.Application
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.AchievementsDataStore
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.AchievementsDataStoreImp
import yaroslavgorbach.koropapps.vocabulary.data.achievements.repo.RepoAchievements
import yaroslavgorbach.koropapps.vocabulary.data.achievements.repo.RepoAchievementsImp
import javax.inject.Singleton

@Module
class DataModuleAchievements {

    @Singleton
    @Provides
    fun provideDataStoreAchievements(context: Application): AchievementsDataStore {
        return AchievementsDataStoreImp(context)
    }

    @Singleton
    @Provides
    fun provideRepoAchievements(
        achievementsDataStore: AchievementsDataStore,
    ): RepoAchievements {
        return RepoAchievementsImp(achievementsDataStore)
    }
}