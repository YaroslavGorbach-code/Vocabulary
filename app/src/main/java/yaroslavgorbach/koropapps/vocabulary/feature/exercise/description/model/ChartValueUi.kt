package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDayOfWeek
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
            return statisticsExerciseValueEntities.map { it.date.formatDayOfWeek() } as ArrayList<String>
        }

    val dates: List<Date>
        get() = statisticsExerciseValueEntities.map { it.date }

    val isEmpty: Boolean
        get() = data.isEmpty() || labels.isEmpty()

    fun getColors(context: Context): IntArray {
        return intArrayOf(
            context.getColorPrimary(),
            context.getColorPrimary(),
            context.getColorPrimary(),
            context.getColorPrimary()
        )
    }

}