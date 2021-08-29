package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDD
import yaroslavgorbach.koropapps.vocabulary.utils.getColorPrimary
import java.util.*

data class ChartUi(private val statisticsValueEntities: List<StatisticsValueEntity>) {

    companion object {
        const val MAX_ITEMS_COUNT = 8
    }

    val data: ArrayList<ArrayList<Int>> = arrayListOf()
        get() {
            val dataList: ArrayList<Int> = statisticsValueEntities.map { it.value } as ArrayList<Int>
            field.add(0, dataList)
            return field
        }

    val labels: ArrayList<String>
        get() {
            return statisticsValueEntities.map { it.date.formatDD() } as ArrayList<String>
        }

    val labelsDate: List<Date>
        get() = statisticsValueEntities.map { it.date }

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