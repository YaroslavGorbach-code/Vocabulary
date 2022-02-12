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

    val valueSubtitleRes
        get() = when (timeEntity.exerciseName) {
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.TAUTOGRAMS -> R.string.made_subtitle
            ExerciseName.REMEMBER_ALL,
            ExerciseName.GAME_I_KNOW_5_NAMES,
            ExerciseName.TEN,
            ExerciseName.SPECIFICATIONS,
            ExerciseName.LINGUISTIC_PYRAMIDS,
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE,
            ExerciseName.STORYTELLER_IMPROVISER,
            ExerciseName.ADVANCED_BINDING,
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
            ExerciseName.MAGIC_NAMING,
            ExerciseName.BUYING_SELLING,
            ExerciseName.CO_AUTHORED_WITH_DAHL,
            ExerciseName.RORSCHACH_TEST,
            ExerciseName.QUESTION_ANSWER,
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
            ExerciseName.WILL_NOT_BE_WORSE,
            ExerciseName.COUP_OF_CONSCIOUSNESS,
            ExerciseName.PROBLEM_SOLVING,
            ExerciseName.COUP,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD,
            ExerciseName.SOUND_COMBINATIONS,
            ExerciseName.DIFFICULT_WORDS,
            ExerciseName.OTHER_ABBREVIATIONS,
            ExerciseName.ASSOCIATIONS -> R.string.passed_subtitle
            else -> R.string.told_subtitle
        }

    val valueTitleRes
        get() = when (timeEntity.exerciseName) {
            ExerciseName.THREE_LETTERS,
            ExerciseName.TAUTOGRAMS -> R.string.number_sentences_for_session
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.PROBLEM_SOLVING,
            ExerciseName.STORYTELLER_IMPROVISER -> R.string.number_stores_for_session
            ExerciseName.REMEMBER_ALL -> R.string.number_letters_for_session
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.number_categories_for_session
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.number_abbreviations
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.string.number_tongue_twisters
            ExerciseName.SOUND_COMBINATIONS -> R.string.number_sound_combinations
            ExerciseName.COUP -> R.string.number_images_for_session
            ExerciseName.QUESTION_ANSWER -> R.string.number_of_questions
            else -> R.string.number_words_for_session
        }

    val timeTitleRes
        get() = when (timeEntity.exerciseName) {
            ExerciseName.THREE_LETTERS,
            ExerciseName.TAUTOGRAMS -> R.string.average_time_on_sentence
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.STORYTELLER_IMPROVISER,
            ExerciseName.PROBLEM_SOLVING,
            ExerciseName.WILL_NOT_BE_WORSE,
            ExerciseName.BUYING_SELLING,
            ExerciseName.QUESTION_ANSWER,
            ExerciseName.COUP_OF_CONSCIOUSNESS,
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
            ExerciseName.COUP,
            ExerciseName.TEN -> R.string.average_time_on_story
            ExerciseName.REMEMBER_ALL -> R.string.average_time_on_letter
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.average_time_on_category
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.average_time_on_abbreviation
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.string.average_time_on_tongue_twister
            ExerciseName.SOUND_COMBINATIONS -> R.string.average_time_on_sound_combinations
            else -> R.string.average_time_on_word
        }
}