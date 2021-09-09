package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.business.training.GetCurrentTrainingIsFinishedInteractor
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

class UpdateStatisticsLevelInteractor(
    private val getStatisticsLevelInteractor: GetStatisticsLevelInteractor,
    private val getCurrentTrainingIsFinishedInteractor: GetCurrentTrainingIsFinishedInteractor,
    private val repoStatistics: RepoStatistics
) {
    operator fun invoke(exerciseType: ExerciseType, summaryTrainingTime: Long): Completable {
        return getStatisticsLevelInteractor().zipWith(getCurrentTrainingIsFinishedInteractor(),
            { currentStatisticsLevelEntity: StatisticsLevelEntity, isTrainingFinished: Boolean ->
                currentStatisticsLevelEntity.dailyTrainingsCompleted =
                    if (exerciseType is ExerciseType.Training && isTrainingFinished) {
                        currentStatisticsLevelEntity.dailyTrainingsCompleted + 1
                    } else {
                        currentStatisticsLevelEntity.dailyTrainingsCompleted
                    }

                currentStatisticsLevelEntity.summaryTrainingTimeMc += summaryTrainingTime
                currentStatisticsLevelEntity.exercisesCompleted += 1

                currentStatisticsLevelEntity
            }).flatMapCompletable(repoStatistics::update)
    }
}