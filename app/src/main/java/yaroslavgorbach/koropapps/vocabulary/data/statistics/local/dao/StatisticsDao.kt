package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity

@Dao
interface StatisticsDao {

    @Insert(onConflict = REPLACE)
    fun insert(statisticsExerciseValueEntity: StatisticsExerciseValueEntity): Completable

    @Insert(onConflict = REPLACE)
    fun insert(statisticsExerciseTimeEntity: StatisticsExerciseTimeEntity): Completable

    @Insert(onConflict = REPLACE)
    fun insert(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    @Insert(onConflict = REPLACE)
    fun insert(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable

    @Update
    fun update(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable

    @Update
    fun update(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    @Query("SELECT * FROM StatisticsExerciseValueEntity WHERE exerciseNameRes = :exerciseNameRes")
    fun observeValue(exerciseNameRes: Int): Observable<List<StatisticsExerciseValueEntity>>

    @Query("SELECT * FROM StatisticsExerciseTimeEntity WHERE exerciseNameRes = :exerciseNameRes")
    fun observeTime(exerciseNameRes: Int): Observable<List<StatisticsExerciseTimeEntity>>

    @Query("SELECT * FROM StatisticsDailyTrainingTimeEntity")
    fun observeDays(): Observable<List<StatisticsDailyTrainingTimeEntity>>

    @Query("SELECT * FROM StatisticsCommonInfoEntity LIMIT 1")
    fun getLevel(): Single<StatisticsCommonInfoEntity>

    @Query("SELECT * FROM StatisticsExerciseValueEntity")
    fun getAllExercisesValue(): Single<List<StatisticsExerciseValueEntity>>
}