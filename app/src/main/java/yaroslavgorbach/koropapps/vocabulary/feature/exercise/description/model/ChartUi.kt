package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDD

data class ChartUi(private val statisticsEntities: List<StatisticsEntity>) {

    companion object {
        const val MAX_ITEMS_COUNT = 8
    }

    val data: ArrayList<ArrayList<Int>> = arrayListOf()
        get() {
            val dataList: ArrayList<Int> = statisticsEntities.map { it.value } as ArrayList<Int>
            field.add(dataList)
            return field
        }

    val labels: ArrayList<String> = arrayListOf()
        get() {
            field.addAll(statisticsEntities.map { it.date.formatDD() })
            return field
        }

    val isEmpty: Boolean
        get() = data.isEmpty() || labels.isEmpty()

}