package yaroslavgorbach.koropapps.vocabulary.di.data.training

import android.app.Application
import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.TrainingDatabase
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import javax.inject.Singleton

@Module
class DataModuleTraining {

    @Singleton
    @Provides
    fun provideRepoTraining(
        localDataStore: TrainingDao
    ): RepoTraining {
        return RepoTrainingImp(localDataStore)
    }

    @Singleton
    @Provides
    fun provideTrainingDatabase(context: Application): TrainingDatabase {
        return Room.databaseBuilder(
            context, TrainingDatabase::class.java, "database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                ContentValues().apply {
                    put("id", 0)
                    put("daysWithoutInterruption", 0)
                    put("isFinished", false)
                    put("numberOfTraining", 0)
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
                    db.insert("TrainingExerciseEntity", OnConflictStrategy.REPLACE, cv)
                }
            }
        }).build()
    }

    @Singleton
    @Provides
    fun provideTrainingDao(database: TrainingDatabase): TrainingDao {
        return database.trainingDao
    }
}