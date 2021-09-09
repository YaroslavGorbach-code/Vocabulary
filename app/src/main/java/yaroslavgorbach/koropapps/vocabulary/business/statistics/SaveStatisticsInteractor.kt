package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

class SaveStatisticsInteractor(
    private val insertStatisticValueInteractor: InsertStatisticValueInteractor,
    private val insertStatisticTimeInteractor: InsertStatisticTimeInteractor,
    private val insertOrUpdateStatisticDayInteractor: InsertOrUpdateStatisticDayInteractor,
    private val updateStatisticsLevelInteractor: UpdateStatisticsLevelInteractor,
) {
    operator fun invoke(
        exerciseType: ExerciseType,
        passedLettersOrWordsCount: Int,
        averageTimeOnWord: Float,
        summaryTimeSpendOnExercise: Long,
    ): Completable {
        return insertStatisticValueInteractor(
            StatisticsEntityFactory().createValueEntity(
                exerciseType.getExerciseName(),
                passedLettersOrWordsCount
            )
        ).andThen(
            insertStatisticTimeInteractor(
                StatisticsEntityFactory().createTimeEntity(
                    exerciseType.getExerciseName(),
                    averageTimeOnWord
                )
            )
        ).andThen(
            insertOrUpdateStatisticDayInteractor(
                StatisticsEntityFactory().createDayEntity(summaryTimeSpendOnExercise)
            )
        ).andThen(
            updateStatisticsLevelInteractor.invoke(exerciseType, summaryTimeSpendOnExercise)
        )
    }
}
