package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity

@Dao
interface StatisticsDao {

    @Insert(onConflict = REPLACE)
    fun insert(statisticsValueEntity: StatisticsValueEntity): Completable

    @Insert(onConflict = REPLACE)
    fun insert(statisticsTimeEntity: StatisticsTimeEntity): Completable

    @Query("SELECT * FROM StatisticsValueEntity WHERE exerciseNameRes = :exerciseNameRes")
    fun observeValue(exerciseNameRes: Int): Observable<List<StatisticsValueEntity>>

    @Query("SELECT * FROM StatisticsTimeEntity WHERE exerciseNameRes = :exerciseNameRes")
    fun observeTime(exerciseNameRes: Int): Observable<List<StatisticsTimeEntity>>
}