package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class GetAllExercisesStatisticsValueInteractor(private val repoStatistics: RepoStatistics) {

    operator fun invoke(): Single<List<StatisticsExerciseValueEntity>> {
        return repoStatistics.getAllExercisesValue()
    }
}