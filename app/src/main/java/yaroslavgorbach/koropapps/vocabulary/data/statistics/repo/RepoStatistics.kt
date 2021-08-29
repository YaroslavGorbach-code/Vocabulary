package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity

interface RepoStatistics {

    fun insert(statisticsValueEntity: StatisticsValueEntity): Completable

    fun insert(statisticsTimeEntity: StatisticsTimeEntity): Completable

    fun observeValue(exerciseNameRes: Int): Observable<List<StatisticsValueEntity>>

    fun observeTime(exerciseNameRes: Int): Observable<List<StatisticsTimeEntity>>

}