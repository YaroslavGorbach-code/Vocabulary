package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class ObserveStatisticsTimeInteractor(private val repoStatistics: RepoStatistics) {

    operator fun invoke(exerciseName: ExerciseName): Observable<List<StatisticsExerciseTimeEntity>> {
        return repoStatistics.observeTime(exerciseName)
            .subscribeOn(Schedulers.io())
    }
}