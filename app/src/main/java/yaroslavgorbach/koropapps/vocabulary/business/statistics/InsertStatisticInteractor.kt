package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class InsertStatisticInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke(statisticsEntity: StatisticsEntity): Completable {
        return repoStatistics.insert(statisticsEntity)
            .subscribeOn(Schedulers.io())
    }
}