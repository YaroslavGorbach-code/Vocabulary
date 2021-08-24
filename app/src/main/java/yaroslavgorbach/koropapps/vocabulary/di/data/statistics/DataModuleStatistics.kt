package yaroslavgorbach.koropapps.vocabulary.di.data.statistics

import android.app.Application
import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.StatisticsDatabase
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatisticsImp
import yaroslavgorbach.koropapps.vocabulary.data.training.local.TrainingDatabase
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import javax.inject.Singleton

@Module
class DataModuleStatistics {

    @Singleton
    @Provides
    fun provideRepoStatistics(
        localDataStore: StatisticsDao
    ): RepoStatistics {
        return RepoStatisticsImp(localDataStore)
    }

    @Singleton
    @Provides
    fun provideStatisticsDatabase(context: Application): StatisticsDatabase {
        return Room.databaseBuilder(
            context, StatisticsDatabase::class.java, "databaseStatistics"
        ).build()
    }

    @Singleton
    @Provides
    fun provideStatisticsDao(database: StatisticsDatabase): StatisticsDao {
        return database.statisticsDao
    }
}