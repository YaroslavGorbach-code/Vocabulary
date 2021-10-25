package yaroslavgorbach.koropapps.vocabulary.di.business.exercises

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ChangeExerciseFavoriteInteractor
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ObserveExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.di.business.achievements.BusinessAchievementsModule
import yaroslavgorbach.koropapps.vocabulary.di.business.training.BusinessTrainingModule
import yaroslavgorbach.koropapps.vocabulary.di.data.exercises.DataModuleExercises
import javax.inject.Singleton

@Module(
    includes = [DataModuleExercises::class]
)
class BusinessExercisesModule {
    @Provides
    fun provideGetExercisesInteractor(repoExercises: RepoExercises): GetExercisesInteractor {
        return GetExercisesInteractor(repoExercises)
    }

    @Provides
    fun provideChangeExerciseFavoriteInteractor(repoExercises: RepoExercises): ChangeExerciseFavoriteInteractor {
        return ChangeExerciseFavoriteInteractor(repoExercises)
    }


    @Provides
    fun provideGetExerciseInteractor(repoExercises: RepoExercises): ObserveExerciseInteractor {
        return ObserveExerciseInteractor(repoExercises)
    }
}