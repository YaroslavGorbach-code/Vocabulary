package yaroslavgorbach.koropapps.vocabulary.di.business.achievements

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ObserveAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ChangeExerciseFavoriteInteractor
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ObserveExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.repo.RepoAchievements
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.di.business.training.BusinessTrainingModule
import yaroslavgorbach.koropapps.vocabulary.di.data.achievements.DataModuleAchievements
import yaroslavgorbach.koropapps.vocabulary.di.data.exercises.DataModuleExercises
import javax.inject.Singleton

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
}