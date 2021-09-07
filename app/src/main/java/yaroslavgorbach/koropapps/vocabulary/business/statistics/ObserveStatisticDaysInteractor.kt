package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class ObserveStatisticDaysInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke(): Observable<List<StatisticsDailyTrainingTimeEntity>> {
        return repoStatistics.observeDays().subscribeOn(Schedulers.io())
    }
}