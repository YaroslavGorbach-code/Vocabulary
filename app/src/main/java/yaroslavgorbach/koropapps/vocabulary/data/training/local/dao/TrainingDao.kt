package yaroslavgorbach.koropapps.vocabulary.data.training.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercises

@Dao
interface TrainingDao {

    @Transaction
    @Query("SELECT * FROM TrainingEntity LIMIT 5")
    fun observe(): Observable<List<TrainingWithExercises>>

    @Insert
    fun insert(trainingEntity: TrainingEntity): Completable
}