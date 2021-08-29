package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class ObserveStatisticsValueInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke(exerciseNameRes: Int): Observable<List<StatisticsValueEntity>> {
        return repoStatistics.observeValue(exerciseNameRes).subscribeOn(Schedulers.io())
    }
}