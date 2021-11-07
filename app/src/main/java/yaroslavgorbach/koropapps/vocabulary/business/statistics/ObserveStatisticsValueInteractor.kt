package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class ObserveStatisticsValueInteractor(private val repoStatistics: RepoStatistics) {

    operator fun invoke(exerciseName: ExerciseName): Observable<List<StatisticsExerciseValueEntity>> {
        return repoStatistics.observeValue(exerciseName).subscribeOn(Schedulers.io())
    }
}