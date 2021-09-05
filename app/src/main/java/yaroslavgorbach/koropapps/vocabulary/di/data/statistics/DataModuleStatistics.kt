package yaroslavgorbach.koropapps.vocabulary.di.data.statistics

import android.app.Application
import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.StatisticsDatabase
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatisticsImp
import java.util.*
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
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                ContentValues().apply {
                    put("id", 0)
                    put("summaryTrainingTime", 0)
                    put("date", Date().time)
                }.also { cv ->
                    db.insert("StatisticsDayEntity", OnConflictStrategy.REPLACE, cv)
                }
            }
        }).build()
    }

    @Singleton
    @Provides
    fun provideStatisticsDao(database: StatisticsDatabase): StatisticsDao {
        return database.statisticsDao
    }
}