package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDD
import yaroslavgorbach.koropapps.vocabulary.utils.getColorPrimary
import java.util.*

data class ChartTimeUi(
    private val statisticsTimeEntities: List<StatisticsTimeEntity>,
    private val exerciseName: ExerciseName
) {

    companion object {
        const val MAX_ITEMS_COUNT = 8
    }

    val data: ArrayList<ArrayList<Int>> = arrayListOf()
        get() {
            val dataList: ArrayList<Int> =
                statisticsTimeEntities.map { it.value.toInt() } as ArrayList<Int>
            field.add(0, dataList)
            return field
        }

    val labels: ArrayList<String>
        get() {
            return statisticsTimeEntities.map { it.date.formatDD() } as ArrayList<String>
        }

    val isEmpty: Boolean
        get() = data.isEmpty() || labels.isEmpty()

    val dates: List<Date>
        get() = statisticsTimeEntities.map { it.date }

    val nameRes: Int
        get() = when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES,
            ExerciseName.ALPHABET_NOUN,
            ExerciseName.ALPHABET_VERBS,
            ExerciseName.ASSOCIATIONS,
            ExerciseName.THREE_LITER_JAR,
            ExerciseName.ANTONYMS_AND_SYNONYMS -> R.string.average_time_on_word
            ExerciseName.TAUTOGRAMS -> R.string.average_time_on_sentence
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.TEN -> R.string.average_time_on_story
            ExerciseName.REMEMBER_ALL -> R.string.average_time_on_letter
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.average_time_on_category
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