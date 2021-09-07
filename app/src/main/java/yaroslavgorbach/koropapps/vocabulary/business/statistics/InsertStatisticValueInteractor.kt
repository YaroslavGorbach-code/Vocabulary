package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class InsertStatisticValueInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke(statisticsExerciseValueEntity: StatisticsExerciseValueEntity): Completable {
        return repoStatistics.insert(statisticsExerciseValueEntity)
            .subscribeOn(Schedulers.io())
    }
}