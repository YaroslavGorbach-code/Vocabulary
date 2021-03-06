package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity

interface RepoStatistics {

    fun insert(statisticsExerciseValueEntity: StatisticsExerciseValueEntity): Completable

    fun insert(statisticsExerciseTimeEntity: StatisticsExerciseTimeEntity): Completable

    fun insert(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    fun insert(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable

    fun update(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable

    fun update(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    fun observeValue(exerciseName: ExerciseName): Observable<List<StatisticsExerciseValueEntity>>

    fun observeTime(exerciseName: ExerciseName): Observable<List<StatisticsExerciseTimeEntity>>

    fun observeDays(): Observable<List<StatisticsDailyTrainingTimeEntity>>

    fun getCommonInfo(): Single<StatisticsCommonInfoEntity>

    fun getAllExercisesValue(): Single<List<StatisticsExerciseValueEntity>>

    fun clearAll(): Completable
}