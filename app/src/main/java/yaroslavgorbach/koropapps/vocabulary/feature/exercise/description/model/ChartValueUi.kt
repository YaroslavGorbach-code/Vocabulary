package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDD
import yaroslavgorbach.koropapps.vocabulary.utils.getColorPrimary
import java.util.*

data class ChartValueUi(
    private val statisticsExerciseValueEntities: List<StatisticsExerciseValueEntity>,
    private val exerciseName: ExerciseName
) {

    companion object {
        const val MAX_ITEMS_COUNT = 8
    }

    val data: ArrayList<ArrayList<Int>> = arrayListOf()
        get() {
            val dataList: ArrayList<Int> =
                statisticsExerciseValueEntities.map { it.value } as ArrayList<Int>
            field.add(0, dataList)
            return field
        }

    val labels: ArrayList<String>
        get() {
            return statisticsExerciseValueEntities.map { it.date.formatDD() } as ArrayList<String>
        }

    val dates: List<Date>
        get() = statisticsExerciseValueEntities.map { it.date }

    val isEmpty: Boolean
        get() = data.isEmpty() || labels.isEmpty()

    val nameRes: Int
        get() = when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES,
            ExerciseName.ALPHABET_NOUN,
            ExerciseName.ALPHABET_VERBS,
            ExerciseName.ASSOCIATIONS,
            ExerciseName.THREE_LITER_JAR,
            ExerciseName.LIST_OF_CATEGORIES,
            ExerciseName.HALF,
            ExerciseName.SPECIFICATIONS,
            ExerciseName.DICTIONARY_ADJECTIVES,
            ExerciseName.DICTIONARY_NOUN,
            ExerciseName.DICTIONARY_VERBS,
            ExerciseName.LINGUISTIC_PYRAMIDS,
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE,
            ExerciseName.STORYTELLER_IMPROVISER,
            ExerciseName.ADVANCED_BINDING,
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
            ExerciseName.MAGIC_NAMING,
            ExerciseName.BUYING_SELLING,
            ExerciseName.CO_AUTHORED_WITH_DAHL,
            ExerciseName.RORSCHACH_TEST,
            ExerciseName.WILL_NOT_BE_WORSE,
            ExerciseName.QUESTION_ANSWER,
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
            ExerciseName.DIFFICULT_WORDS,
            ExerciseName.COUP_OF_CONSCIOUSNESS,
            ExerciseName.ANTONYMS_AND_SYNONYMS -> R.string.number_words_for_session
            ExerciseName.THREE_LETTERS,
            ExerciseName.TAUTOGRAMS -> R.string.number_sentences_for_session
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.TEN -> R.string.number_stores_for_session
            ExerciseName.REMEMBER_ALL -> R.string.number_letters_for_session
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.number_categories_for_session
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.number_abbreviations
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.string.number_tongue_twisters
            ExerciseName.SOUND_COMBINATIONS -> R.string.number_sound_combinations
        }

    fun getColors(context: Context): IntArray {
        return intArrayOf(
            context.getColorPrimary(),
            context.getColorPrimary(),
            context.getColorPrimary(),
            context.getColorPrimary()
        )
    }

}