package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseCategoryMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

class SaveStatisticsInteractor(
    private val insertStatisticValueInteractor: InsertStatisticValueInteractor,
    private val insertStatisticTimeInteractor: InsertStatisticTimeInteractor,
    private val insertOrUpdateStatisticDayInteractor: InsertOrUpdateStatisticDayInteractor,
    private val updateStatisticsCommonInfoInteractor: UpdateStatisticsCommonInfoInteractor,
) {
    companion object {
        private const val DICTIONARY_ADJECTIVES_WORDS_NORM = 46
        private const val DICTIONARY_NOUNS_WORDS_NORM = 54
        private const val DICTIONARY_VERBS_WORDS_NORM = 42
    }

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
            updateStatisticsCommonInfoInteractor.invoke(exerciseType, summaryTimeSpendOnExercise)
        )
    }

}


