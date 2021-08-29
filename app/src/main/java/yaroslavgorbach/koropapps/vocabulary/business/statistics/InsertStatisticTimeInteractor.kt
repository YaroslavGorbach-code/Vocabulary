package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class InsertStatisticTimeInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke(statisticsTimeEntity: StatisticsTimeEntity): Completable {
        return repoStatistics.insert(statisticsTimeEntity)
            .subscribeOn(Schedulers.io())
    }
}