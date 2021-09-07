package yaroslavgorbach.koropapps.vocabulary.di.data.statistics

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.StatisticsDatabase
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatisticsImp
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