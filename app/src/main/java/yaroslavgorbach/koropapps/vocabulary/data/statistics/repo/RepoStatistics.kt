package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDayEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity

interface RepoStatistics {

    fun insert(statisticsValueEntity: StatisticsValueEntity): Completable

    fun insert(statisticsTimeEntity: StatisticsTimeEntity): Completable

    fun insert(statisticsDayEntity: StatisticsDayEntity): Completable

    fun update(statisticsDayEntity: StatisticsDayEntity): Completable

    fun observeValue(exerciseNameRes: Int): Observable<List<StatisticsValueEntity>>

    fun observeTime(exerciseNameRes: Int): Observable<List<StatisticsTimeEntity>>

    fun observeDays(): Observable<List<StatisticsDayEntity>>
}