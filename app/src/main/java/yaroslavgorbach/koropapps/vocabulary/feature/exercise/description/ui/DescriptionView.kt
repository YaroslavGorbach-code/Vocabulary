package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.view.View
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import im.dacer.androidcharts.LineView
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartTimeUi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartValueUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class DescriptionView(
    private val binding: FragmentDescriptionBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onOpenExercise()
        fun onBack()
        fun onNextChartValue()
        fun onPreviousChartValue()
        fun onNextChartTime()
        fun onPreviousChartTime()
        fun onAddToFavorite()
    }

    init {
        initActions()
    }

    private fun initActions() {
        binding.openExercise.setOnClickListener {
            callback.onOpenExercise()
        }

        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_add_to_favorite -> {
                    callback.onAddToFavorite()
                }
            }
            true
        }

        binding.chart.nextData.setOnClickListener {
            callback.onNextChartValue()
        }

        binding.chart.prevData.setOnClickListener {
            callback.onPreviousChartValue()
        }

        binding.chartTime.nextData.setOnClickListener {
            callback.onNextChartTime()
        }

        binding.chartTime.prevData.setOnClickListener {
            callback.onPreviousChartTime()
        }

        binding.scrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) {
                    binding.openExercise.hide()
                } else {
                    binding.openExercise.show()
                }
            })
    }

    fun setDescriptionRes(descriptionRes: Int) {
        binding.descriptionText.text = binding.getString(descriptionRes)
    }

    fun setExerciseIconRes(exerciseIconRes: Int) {
        with(binding.getDrawable(exerciseIconRes)) {
            binding.icon.setImageDrawable(this)
        }
    }

    fun setExerciseFavorite(isFavorite: Boolean) {
        val menuItem = binding.toolbar.menu[0]

        if (isFavorite) {
            menuItem.icon = binding.getDrawable(R.drawable.ic_star_yellow)
        } else {
            menuItem.icon = binding.getDrawable(R.drawable.ic_star)
        }
    }

    fun setExerciseNameRes(exerciseNameRes: Int) {
        binding.toolbar.title = binding.getString(exerciseNameRes)
    }

    fun setChartValue(chartValueUi: ChartValueUi) {
        binding.chart.statisticsText.text = binding.getString(chartValueUi.nameRes)

        if (chartValueUi.isEmpty) {
            showNoChartValueData()
        } else {
            binding.chart.chart.setDrawDotLine(false)
            binding.chart.chart.setShowPopup(LineView.SHOW_POPUPS_All)
            binding.chart.chart.setBottomTextList(chartValueUi.labels)
            binding.chart.chart.setColorArray(chartValueUi.getColors(binding.root.context))
            binding.chart.chart.setDataList(chartValueUi.data)
        }
    }

    private fun showNoChartValueData() {
        binding.chart.noData.visibility = View.VISIBLE
        binding.chart.chart.visibility = View.GONE
    }

    fun setChartTime(chartTimeUi: ChartTimeUi) {
        binding.chartTime.statisticsText.text = binding.getString(chartTimeUi.nameRes)

        if (chartTimeUi.isEmpty) {
            showNoChartTimeData()
        } else {
            binding.chartTime.chart.setDrawDotLine(false)
            binding.chartTime.chart.setShowPopup(LineView.SHOW_POPUPS_All)
            binding.chartTime.chart.setBottomTextList(chartTimeUi.labels)
            binding.chartTime.chart.setColorArray(chartTimeUi.getColors(binding.root.context))
            binding.chartTime.chart.setDataList(chartTimeUi.data)
        }
    }

    private fun showNoChartTimeData() {
        binding.chartTime.noData.visibility = View.VISIBLE
        binding.chartTime.chart.visibility = View.GONE
    }

}