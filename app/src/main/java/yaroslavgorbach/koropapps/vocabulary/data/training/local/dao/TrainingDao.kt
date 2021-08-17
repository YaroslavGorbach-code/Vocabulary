package yaroslavgorbach.koropapps.vocabulary.data.training.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

@Dao
interface TrainingDao {

    @Transaction
    @Query("SELECT * FROM TrainingEntity")
    fun observe(): Observable<List<TrainingWithExercisesEntity>>

    @Insert
    fun insertTraining(trainingEntity: TrainingEntity): Completable

    @Insert
    fun insertExercises(trainingExercises: List<TrainingExerciseEntity>): Completable
}