package yaroslavgorbach.koropapps.vocabulary.data.training.local.dao

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity

@Dao
interface ExerciseTrainingDao {
    @Insert
    fun insert(trainingExerciseEntity: TrainingExerciseEntity): Completable

    @Insert
    fun insert(trainingExerciseEntity: List<TrainingExerciseEntity>): Completable
}