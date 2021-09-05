package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDayEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class InsertOrUpdateStatisticDayInteractor(
    private val repoStatistics: RepoStatistics,
    private val observeStatisticDaysInteractor: ObserveStatisticDaysInteractor
) {
    operator fun invoke(statisticsDayEntity: StatisticsDayEntity): Completable {
        return observeStatisticDaysInteractor()
            .firstOrError()
            .map { it.last() }
            .flatMapCompletable { statisticLast ->
                if (statisticLast.date.isToday()) {
                    statisticLast.summaryTrainingTime += statisticsDayEntity.summaryTrainingTime

                    repoStatistics.update(statisticLast)
                        .subscribeOn(Schedulers.io())
                } else {
                    repoStatistics.insert(statisticsDayEntity)
                        .subscribeOn(Schedulers.io())
                }
            }
    }
}