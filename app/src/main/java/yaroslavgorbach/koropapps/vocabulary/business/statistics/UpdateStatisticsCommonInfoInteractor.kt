package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.business.training.GetCurrentTrainingIsFinishedInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

class UpdateStatisticsCommonInfoInteractor(
    private val getStatisticsCommonInfoInteractor: GetStatisticsCommonInfoInteractor,
    private val getCurrentTrainingIsFinishedInteractor: GetCurrentTrainingIsFinishedInteractor,
    private val repoStatistics: RepoStatistics
) {
    operator fun invoke(exerciseType: ExerciseType, summaryTrainingTime: Long): Completable {
        return getStatisticsCommonInfoInteractor().zipWith(getCurrentTrainingIsFinishedInteractor(),
            { currentStatisticsCommonInfoEntity: StatisticsCommonInfoEntity, isTrainingFinished: Boolean ->

                currentStatisticsCommonInfoEntity.dailyTrainingsCompleted =
                    if (exerciseType is ExerciseType.Training && isTrainingFinished) {
                        currentStatisticsCommonInfoEntity.dailyTrainingsCompleted + 1
                    } else {
                        currentStatisticsCommonInfoEntity.dailyTrainingsCompleted
                    }

                currentStatisticsCommonInfoEntity.summaryTrainingTimeMc += summaryTrainingTime

                when (exerciseType.getExerciseCategory()) {
                    ExerciseCategory.COMMUNICATION -> currentStatisticsCommonInfoEntity.summaryTrainingTimeCommunicationMc += summaryTrainingTime
                    ExerciseCategory.VOCABULARY -> currentStatisticsCommonInfoEntity.summaryTrainingTimeVocabularyMc += summaryTrainingTime
                    ExerciseCategory.DICTION_AND_ARTICULATION -> currentStatisticsCommonInfoEntity.summaryTrainingTimeDictionMc += summaryTrainingTime
                    ExerciseCategory.SENSE_OF_HUMOR -> currentStatisticsCommonInfoEntity.summaryTrainingTimeSenseOfHumorMc += summaryTrainingTime
                }

                currentStatisticsCommonInfoEntity.exercisesCompleted += 1

                currentStatisticsCommonInfoEntity
            }).flatMapCompletable(repoStatistics::update)
    }
}