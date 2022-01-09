package yaroslavgorbach.koropapps.vocabulary.data.statistics.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import yaroslavgorbach.koropapps.vocabulary.data.common.DateConverter
import yaroslavgorbach.koropapps.vocabulary.data.common.ExerciseNameConverter
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity

@TypeConverters(DateConverter::class, ExerciseNameConverter::class)
@Database(
    exportSchema = true,
    entities = [
        StatisticsExerciseValueEntity::class,
        StatisticsExerciseTimeEntity::class,
        StatisticsDailyTrainingTimeEntity::class,
        StatisticsCommonInfoEntity::class
    ],
    version = 2
)
abstract class StatisticsDatabase : RoomDatabase() {
    abstract val statisticsDao: StatisticsDao
}

val STATISTICS_MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE StatisticsCommonInfoEntity ADD COLUMN summaryTrainingTimeSenseOfHumorMc INTEGER NOT NULL DEFAULT 'null'")
    }
}