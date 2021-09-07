package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class InsertStatisticTimeInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke(statisticsExerciseTimeEntity: StatisticsExerciseTimeEntity): Completable {
        return repoStatistics.insert(statisticsExerciseTimeEntity)
            .subscribeOn(Schedulers.io())
    }
}