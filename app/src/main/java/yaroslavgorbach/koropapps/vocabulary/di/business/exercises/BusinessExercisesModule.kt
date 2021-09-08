package yaroslavgorbach.koropapps.vocabulary.di.business.exercises

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.di.business.training.BusinessTrainingModule
import yaroslavgorbach.koropapps.vocabulary.di.data.exercises.DataModuleExercises
import javax.inject.Singleton

@Module(includes = [DataModuleExercises::class, BusinessTrainingModule::class])
class BusinessExercisesModule {
    @Singleton
    @Provides
    fun provideGetExercisesInteractor(repoExercises: RepoExercises): GetExercisesInteractor {
        return GetExercisesInteractor(repoExercises)
    }
}