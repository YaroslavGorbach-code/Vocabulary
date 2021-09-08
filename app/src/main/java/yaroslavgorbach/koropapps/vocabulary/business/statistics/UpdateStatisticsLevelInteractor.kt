package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class UpdateStatisticsLevelInteractor(
    private val getStatisticsLevelInteractor: GetStatisticsLevelInteractor,
    private val repoStatistics: RepoStatistics
) {
    operator fun invoke(statisticsLevelEntity: StatisticsLevelEntity): Completable {
        return getStatisticsLevelInteractor().map {
            it.dailyTrainingsCompleted += statisticsLevelEntity.dailyTrainingsCompleted
            it.exercisesCompleted += statisticsLevelEntity.exercisesCompleted
            it.summaryTrainingTimeMc += statisticsLevelEntity.summaryTrainingTimeMc
            it
        }.flatMapCompletable(repoStatistics::update)
    }
}