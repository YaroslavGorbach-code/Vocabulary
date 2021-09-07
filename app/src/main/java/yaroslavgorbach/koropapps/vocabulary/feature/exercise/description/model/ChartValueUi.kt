package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDD
import yaroslavgorbach.koropapps.vocabulary.utils.getColorPrimary
import java.util.*

data class ChartValueUi(
    private val statisticsValueEntities: List<StatisticsValueEntity>,
    private val exerciseName: ExerciseName
) {

    companion object {
        const val MAX_ITEMS_COUNT = 8
    }

    val data: ArrayList<ArrayList<Int>> = arrayListOf()
        get() {
            val dataList: ArrayList<Int> =
                statisticsValueEntities.map { it.value } as ArrayList<Int>
            field.add(0, dataList)
            return field
        }

    val labels: ArrayList<String>
        get() {
            return statisticsValueEntities.map { it.date.formatDD() } as ArrayList<String>
        }

    val dates: List<Date>
        get() = statisticsValueEntities.map { it.date }

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
            ExerciseName.ANTONYMS_AND_SYNONYMS -> R.string.number_words_for_session
            ExerciseName.THREE_LETTERS,
            ExerciseName.TAUTOGRAMS -> R.string.number_sentences_for_session
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.TEN -> R.string.number_stores_for_session
            ExerciseName.REMEMBER_ALL -> R.string.number_letters_for_session
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.number_categories_for_session
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