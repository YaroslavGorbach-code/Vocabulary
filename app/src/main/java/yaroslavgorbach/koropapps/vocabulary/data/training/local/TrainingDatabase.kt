package yaroslavgorbach.koropapps.vocabulary.data.training.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import java.util.*

@TypeConverters(TrainingDatabase.DateConverter::class)
@Database(entities = [TrainingEntity::class, TrainingExerciseEntity::class], version = 1)
abstract class TrainingDatabase : RoomDatabase() {

    abstract val trainingDao: TrainingDao

    object DateConverter {
        @TypeConverter
        fun toDate(dateLong: Long?): Date? {
            return dateLong?.let { Date(it) }
        }

        @TypeConverter
        fun fromDate(date: Date?): Long? {
            return date?.time
        }
    }

}