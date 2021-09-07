package yaroslavgorbach.koropapps.vocabulary.di.data.training

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.training.local.TrainingDatabase
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import javax.inject.Singleton

@Module
class DataModuleTraining {

    @Singleton
    @Provides
    fun provideRepoTraining(
        localDataStore: TrainingDao
    ): RepoTraining {
        return RepoTrainingImp(localDataStore)
    }

    @Singleton
    @Provides
    fun provideTrainingDatabase(context: Application): TrainingDatabase {
        return Room.databaseBuilder(
            context, TrainingDatabase::class.java, "database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTrainingDao(database: TrainingDatabase): TrainingDao {
        return database.trainingDao
    }
}