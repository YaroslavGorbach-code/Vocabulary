package yaroslavgorbach.koropapps.vocabulary.data.statistics.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yaroslavgorbach.koropapps.vocabulary.data.common.DateConverter
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDayEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity

@TypeConverters(DateConverter::class)
@Database(
    entities = [StatisticsValueEntity::class, StatisticsTimeEntity::class, StatisticsDayEntity::class],
    version = 1
)
abstract class StatisticsDatabase : RoomDatabase() {
    abstract val statisticsDao: StatisticsDao
}