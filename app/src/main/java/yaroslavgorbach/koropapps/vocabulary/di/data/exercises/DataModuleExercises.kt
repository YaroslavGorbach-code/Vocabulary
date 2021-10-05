package yaroslavgorbach.koropapps.vocabulary.di.data.exercises

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.ExercisesDataStore
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.ExercisesDataStoreImp
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp
import javax.inject.Singleton

@Module
class DataModuleExercises {
    @Singleton
    @Provides
    fun provideDataStoreExercises(): ExercisesDataStore {
        return ExercisesDataStoreImp()
    }

    @Singleton
    @Provides
    fun provideRepoExercises(
        exercisesDataStore: ExercisesDataStore,
        context: Application
    ): RepoExercises {
        return RepoExercisesImp(exercisesDataStore, context)
    }
}