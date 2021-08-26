package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity

interface RepoStatistics {

    fun insert(statisticsEntity: StatisticsEntity): Completable

    fun observe(exerciseNameRes: Int): Observable<List<StatisticsEntity>>

}