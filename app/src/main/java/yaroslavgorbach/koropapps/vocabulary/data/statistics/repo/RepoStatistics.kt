package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity

interface RepoStatistics {

    fun insert(statisticsExerciseValueEntity: StatisticsExerciseValueEntity): Completable

    fun insert(statisticsExerciseTimeEntity: StatisticsExerciseTimeEntity): Completable

    fun insert(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    fun insert(statisticsLevelEntity: StatisticsLevelEntity): Completable

    fun update(statisticsLevelEntity: StatisticsLevelEntity): Completable

    fun update(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable

    fun observeValue(exerciseNameRes: Int): Observable<List<StatisticsExerciseValueEntity>>

    fun observeTime(exerciseNameRes: Int): Observable<List<StatisticsExerciseTimeEntity>>

    fun observeDays(): Observable<List<StatisticsDailyTrainingTimeEntity>>

    fun getLevel(): Single<StatisticsLevelEntity>
}