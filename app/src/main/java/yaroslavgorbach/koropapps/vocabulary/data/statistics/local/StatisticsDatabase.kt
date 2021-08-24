package yaroslavgorbach.koropapps.vocabulary.data.statistics.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yaroslavgorbach.koropapps.vocabulary.data.common.DateConverter
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity

@TypeConverters(DateConverter::class)
@Database(
    entities = [StatisticsEntity::class],
    version = 1
)
abstract class StatisticsDatabase : RoomDatabase() {
    abstract val statisticsDao: StatisticsDao
}