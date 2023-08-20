package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity

@Dao
interface StatisticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(statisticsExerciseValueEntity: StatisticsExerciseValueEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(statisticsExerciseTimeEntity: StatisticsExerciseTimeEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable

    @Update
    fun update(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable

    @Update
    fun update(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    @Query("SELECT * FROM StatisticsExerciseValueEntity WHERE exerciseName = :exerciseName")
    fun observeValue(exerciseName: ExerciseName): Observable<List<StatisticsExerciseValueEntity>>

    @Query("SELECT * FROM StatisticsExerciseTimeEntity WHERE exerciseName = :exerciseName")
    fun observeTime(exerciseName: ExerciseName): Observable<List<StatisticsExerciseTimeEntity>>

    @Query("SELECT * FROM StatisticsDailyTrainingTimeEntity")
    fun observeDays(): Observable<List<StatisticsDailyTrainingTimeEntity>>

    @Query("SELECT * FROM StatisticsCommonInfoEntity LIMIT 1")
    fun getLevel(): Single<StatisticsCommonInfoEntity>

    @Query("SELECT * FROM StatisticsExerciseValueEntity")
    fun getAllExercisesValue(): Single<List<StatisticsExerciseValueEntity>>

    @Query("DELETE FROM StatisticsExerciseValueEntity")
    fun clearExercisesValue(): Completable

    @Query("DELETE FROM StatisticsExerciseTimeEntity")
    fun clearExercisesTime(): Completable

    @Query("DELETE FROM StatisticsDailyTrainingTimeEntity")
    fun cleaDailyTrainingTime(): Completable

    @Query("DELETE FROM StatisticsCommonInfoEntity")
    fun cleaCommonInfo(): Completable
}