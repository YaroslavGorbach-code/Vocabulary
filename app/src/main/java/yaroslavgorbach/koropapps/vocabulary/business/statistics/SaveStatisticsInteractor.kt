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
    private val achieveAchievementInteractor: AchieveAchievementInteractor
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
        ).andThen(achieveAchievements(exerciseType.getExerciseName(), passedLettersOrWordsCount))
    }

    private fun achieveAchievements(
        exerciseName: ExerciseName,
        passedLettersOrWordsCount: Int
    ): Completable {
        return Completable.create { emitter ->
            if (exerciseName == ExerciseName.TONGUE_TWISTERS_VERY_HARD
                || exerciseName == ExerciseName.TONGUE_TWISTERS_HARD
                || exerciseName == ExerciseName.TONGUE_TWISTERS_EASY
                && passedLettersOrWordsCount > 0
            ) {
                achieveAchievementInteractor(AchievementName.FIRST_TONGUE_TWISTER_COMPLETE)
            }

            if (ExerciseNameToExerciseCategoryMapper().map(exerciseName) == ExerciseCategory.COMMUNICATION) {
                achieveAchievementInteractor(AchievementName.FIRST_IMPROVISATION_COMPLETE)
            }

            if (ExerciseNameToExerciseCategoryMapper().map(exerciseName) == ExerciseCategory.VOCABULARY) {
                achieveAchievementInteractor(AchievementName.FIRST_VOCABULARY_COMPLETE)
            }

            if (exerciseName == ExerciseName.DICTIONARY_VERBS && passedLettersOrWordsCount >= DICTIONARY_VERBS_WORDS_NORM) {
                achieveAchievementInteractor(AchievementName.DICTIONARY_VERBS_OVER_NORM)
            }

            if (exerciseName == ExerciseName.DICTIONARY_NOUN && passedLettersOrWordsCount >= DICTIONARY_NOUNS_WORDS_NORM) {
                achieveAchievementInteractor(AchievementName.DICTIONARY_NOUNS_OVER_NORM)
            }

            if (exerciseName == ExerciseName.DICTIONARY_ADJECTIVES && passedLettersOrWordsCount >= DICTIONARY_ADJECTIVES_WORDS_NORM) {
                achieveAchievementInteractor(AchievementName.DICTIONARY_ADJECTIVES_OVER_NORM)
            }

            emitter.onComplete()
        }
    }
}


