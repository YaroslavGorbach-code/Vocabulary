package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity

@Dao
interface StatisticsDao {

    @Insert(onConflict = REPLACE)
    fun insert(statisticsEntity: StatisticsEntity): Completable

    @Query("SELECT * FROM statisticsEntity WHERE exerciseNameRes = :exerciseNameRes")
    fun observe(exerciseNameRes: Int): Observable<List<StatisticsEntity>>
}