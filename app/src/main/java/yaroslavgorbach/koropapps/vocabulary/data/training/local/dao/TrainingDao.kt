package yaroslavgorbach.koropapps.vocabulary.data.training.local.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

@Dao
interface TrainingDao {
    @Transaction
    @Query("SELECT * FROM TrainingEntity")
    fun observeTrainingWithExercisesEntity(): Observable<List<TrainingWithExercisesEntity>>

    @Insert
    fun insertTraining(trainingEntity: TrainingEntity): Completable

    @Insert
    fun insertExercises(trainingExercises: List<TrainingExerciseEntity>): Completable

    @Update
    fun updateExercise(trainingExercise: TrainingExerciseEntity): Completable

    @Query("SELECT * FROM trainingExerciseEntity WHERE id = :exerciseId")
    fun observeExercise(exerciseId: Long): Observable<TrainingExerciseEntity>

    @Query("SELECT * FROM trainingExerciseEntity WHERE id = :exerciseId")
    fun getExercise(exerciseId: Long): Single<TrainingExerciseEntity>
}