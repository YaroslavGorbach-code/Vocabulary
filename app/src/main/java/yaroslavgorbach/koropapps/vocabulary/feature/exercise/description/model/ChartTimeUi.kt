package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDD
import yaroslavgorbach.koropapps.vocabulary.utils.getColorPrimary
import java.util.*

data class ChartTimeUi(
    private val statisticsExerciseTimeEntities: List<StatisticsExerciseTimeEntity>,
    private val exerciseName: ExerciseName
) {

    companion object {
        const val MAX_ITEMS_COUNT = 8
    }

    val data: ArrayList<ArrayList<Int>> = arrayListOf()
        get() {
            val dataList: ArrayList<Int> =
                statisticsExerciseTimeEntities.map { it.value.toInt() } as ArrayList<Int>
            field.add(0, dataList)
            return field
        }

    val labels: ArrayList<String>
        get() {
            return statisticsExerciseTimeEntities.map { it.date.formatDD() } as ArrayList<String>
        }

    val isEmpty: Boolean
        get() = data.isEmpty() || labels.isEmpty()

    val dates: List<Date>
        get() = statisticsExerciseTimeEntities.map { it.date }

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
            ExerciseName.ADVANCED_BINDING,
            ExerciseName.STORYTELLER_IMPROVISER,
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
            ExerciseName.MAGIC_NAMING,
            ExerciseName.BUYING_SELLING,
            ExerciseName.CO_AUTHORED_WITH_DAHL,
            ExerciseName.RORSCHACH_TEST,
            ExerciseName.WILL_NOT_BE_WORSE,
            ExerciseName.QUESTION_ANSWER,
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
            ExerciseName.ANTONYMS_AND_SYNONYMS -> R.string.average_time_on_word
            ExerciseName.THREE_LETTERS,
            ExerciseName.TAUTOGRAMS -> R.string.average_time_on_sentence
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.TEN -> R.string.average_time_on_story
            ExerciseName.REMEMBER_ALL -> R.string.average_time_on_letter
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.average_time_on_category
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.average_time_on_abbreviation
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