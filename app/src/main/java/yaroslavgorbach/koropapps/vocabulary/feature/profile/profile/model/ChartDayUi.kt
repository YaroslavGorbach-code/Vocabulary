package yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.model

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDD
import yaroslavgorbach.koropapps.vocabulary.utils.getColorPrimary
import java.util.*

data class ChartDayUi(private val statisticsDailyTrainingTimeEntity: List<StatisticsDailyTrainingTimeEntity>) {

    companion object {
        const val MAX_ITEMS_COUNT = 7
        const val ONE_MINUTE = 60000
    }

    val data: ArrayList<ArrayList<Int>> = arrayListOf()
        get() {
            val dataList: ArrayList<Int> =
                statisticsDailyTrainingTimeEntity.map { it.summaryTrainingTimeMc.toInt() / ONE_MINUTE } as ArrayList<Int>
            field.add(0, dataList)
            return field
        }

    val labels: ArrayList<String>
        get() = statisticsDailyTrainingTimeEntity.map { it.date.formatDD() } as ArrayList<String>

    val isEmpty: Boolean
        get() = data.isEmpty() || labels.isEmpty()

    val dates: List<Date>
        get() = statisticsDailyTrainingTimeEntity.map { it.date }

    fun getColors(context: Context): IntArray {
        return intArrayOf(
            context.getColorPrimary(),
            context.getColorPrimary(),
            context.getColorPrimary(),
            context.getColorPrimary()
        )
    }
}
