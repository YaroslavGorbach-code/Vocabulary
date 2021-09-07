package yaroslavgorbach.koropapps.vocabulary.data.statistics.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yaroslavgorbach.koropapps.vocabulary.data.common.DateConverter
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity

@TypeConverters(DateConverter::class)
@Database(
    entities = [
        StatisticsExerciseValueEntity::class,
        StatisticsExerciseTimeEntity::class,
        StatisticsDailyTrainingTimeEntity::class,
        StatisticsLevelEntity::class
    ],
    version = 1
)
abstract class StatisticsDatabase : RoomDatabase() {
    abstract val statisticsDao: StatisticsDao
}