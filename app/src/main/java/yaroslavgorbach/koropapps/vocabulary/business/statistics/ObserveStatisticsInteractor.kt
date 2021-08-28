package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class ObserveStatisticsInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke(exerciseNameRes: Int): Observable<List<StatisticsEntity>> {
        return repoStatistics.observe(exerciseNameRes).subscribeOn(Schedulers.io())
    }
}