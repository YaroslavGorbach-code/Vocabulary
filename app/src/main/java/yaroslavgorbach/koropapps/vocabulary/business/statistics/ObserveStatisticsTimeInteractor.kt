package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class ObserveStatisticsTimeInteractor(private val repoStatistics: RepoStatistics) {

    operator fun invoke(exerciseNameRes: Int): Observable<List<StatisticsExerciseTimeEntity>> {
        return repoStatistics.observeTime(exerciseNameRes)
            .subscribeOn(Schedulers.io())
    }
}