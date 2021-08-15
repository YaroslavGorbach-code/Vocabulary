package yaroslavgorbach.koropapps.vocabulary.data.training.local.dao

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.ExerciseTrainingEntity

@Dao
interface ExerciseTrainingDao {
    @Insert
    fun insert(exerciseTrainingEntity: ExerciseTrainingEntity): Completable

    @Insert
    fun insert(exerciseTrainingEntity: List<ExerciseTrainingEntity>): Completable
}