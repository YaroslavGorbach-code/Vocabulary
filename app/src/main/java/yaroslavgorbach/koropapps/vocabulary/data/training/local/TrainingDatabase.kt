package yaroslavgorbach.koropapps.vocabulary.data.training.local

import android.content.ContentValues
import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.ExerciseTrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.ExerciseTrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import java.util.*

@TypeConverters(TrainingDatabase.DateConverter::class)
@Database(entities = [TrainingEntity::class, ExerciseTrainingEntity::class], version = 1)
abstract class TrainingDatabase : RoomDatabase() {

    abstract val trainingDao: TrainingDao
    abstract val exerciseTrainingDao: ExerciseTrainingDao

    companion object {
        private lateinit var INSTANCE: TrainingDatabase

        fun getInstance(context: Context): TrainingDatabase {
            synchronized(TrainingDatabase::class.java) {
                if (::INSTANCE.isInitialized.not()) {
                    INSTANCE = Room.databaseBuilder(
                        context, TrainingDatabase::class.java, "database"
                    ).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            createTestData(db)
                        }
                    }).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        private fun createTestData(db: SupportSQLiteDatabase) {
            ContentValues().apply {
                put("id", 0)
            }.also { cv ->
                db.insert("TrainingEntity", OnConflictStrategy.REPLACE, cv)
            }
            ContentValues().apply {
                put("id", 0)
                put("trainingId", 0)
                put("name", ExerciseName.NARRATOR_VERBS.name)
                put("aim", 0)
                put("performed", 0)
            }.also { cv ->
                db.insert("ExerciseTrainingEntity", OnConflictStrategy.REPLACE, cv)
            }
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