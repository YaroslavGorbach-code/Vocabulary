package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDayOfWeek
import yaroslavgorbach.koropapps.vocabulary.utils.formatDayOfWeekText

data class StatisticItemUi(
    private val timeEntity: StatisticsExerciseTimeEntity,
    private val valueEntity: StatisticsExerciseValueEntity,
    var isChosen: Boolean = false
) {
    val dayOfWeek = timeEntity.date.formatDayOfWeek()

    val dayOfWeekText = timeEntity.date.formatDayOfWeekText()

    val id = timeEntity.date.time

    val value = valueEntity.value

    val time = timeEntity.value

    val valueTitleRes
        get() = when (timeEntity.exerciseName) {
            ExerciseName.THREE_LETTERS,
            ExerciseName.TAUTOGRAMS -> R.string.number_sentences_for_session
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.PROBLEM_SOLVING,
            ExerciseName.TEN -> R.string.number_stores_for_session
            ExerciseName.REMEMBER_ALL -> R.string.number_letters_for_session
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.number_categories_for_session
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.number_abbreviations
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.string.number_tongue_twisters
            ExerciseName.SOUND_COMBINATIONS -> R.string.number_sound_combinations
            ExerciseName.COUP -> R.string.number_images_for_session
            else -> R.string.number_words_for_session
        }

    val timeTitleRes
        get() = when (timeEntity.exerciseName) {
            ExerciseName.THREE_LETTERS,
            ExerciseName.TAUTOGRAMS -> R.string.average_time_on_sentence
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.PROBLEM_SOLVING,
            ExerciseName.TEN -> R.string.average_time_on_story
            ExerciseName.REMEMBER_ALL -> R.string.average_time_on_letter
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.average_time_on_category
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.average_time_on_abbreviation
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.string.average_time_on_tongue_twister
            ExerciseName.SOUND_COMBINATIONS -> R.string.average_time_on_sound_combinations
            ExerciseName.COUP -> R.string.average_time_on_image
            else -> R.string.average_time_on_word
        }
}