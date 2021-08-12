package yaroslavgorbach.koropapps.vocabulary.data.training.local

import android.content.Context
import androidx.room.*
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.ExerciseTrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import java.util.*

@TypeConverters(TrainingDatabase.DateConverter::class)
@Database(entities = [TrainingEntity::class, ExerciseTrainingEntity::class], version = 1)
abstract class TrainingDatabase : RoomDatabase() {

    abstract val trainingDao: TrainingDao

    companion object {
        private lateinit var INSTANCE: TrainingDatabase

        fun getInstance(context: Context): TrainingDatabase {
            synchronized(TrainingDatabase::class.java) {
                if (::INSTANCE.isInitialized.not()) {
                    INSTANCE = Room.databaseBuilder(
                        context, TrainingDatabase::class.java, "database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

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