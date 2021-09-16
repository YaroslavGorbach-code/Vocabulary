package yaroslavgorbach.koropapps.vocabulary.di.data.exercises

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp
import javax.inject.Singleton

@Module
class DataModuleExercises {
    @Singleton
    @Provides
    fun provideRepoExercises(): RepoExercises {
        return RepoExercisesImp()
    }
}