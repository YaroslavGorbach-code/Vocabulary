package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.view.View
import im.dacer.androidcharts.LineView
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class DescriptionView(
    private val binding: FragmentDescriptionBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onOpenExercise()
        fun onBack()
        fun onNextChart()
        fun onPreviousChart()
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
        binding.chartLayout.nextData.setOnClickListener {
            callback.onNextChart()
        }
        binding.chartLayout.prevData.setOnClickListener {
            callback.onPreviousChart()
        }
    }

    fun setDescription(description: Description) {
        binding.descriptionText.text = binding.getString(description.textRes)
        with(binding.getDrawable(description.exerciseIconRes)) {
            binding.icon1.setImageDrawable(this)
            binding.icon2.setImageDrawable(this)
            binding.icon3.setImageDrawable(this)
            binding.icon4.setImageDrawable(this)
            binding.icon5.setImageDrawable(this)
        }
        binding.toolbar.title = binding.getString(description.exerciseName.id)
    }

    fun setChart(chartUi: ChartUi) {
        if (chartUi.isEmpty) {
            showNoChartData()
        } else {
            binding.chartLayout.chart.setDrawDotLine(false)
            binding.chartLayout.chart.setShowPopup(LineView.SHOW_POPUPS_All)
            binding.chartLayout.chart.setBottomTextList(chartUi.labels)
            binding.chartLayout.chart.setColorArray(chartUi.getColors(binding.root.context))
            binding.chartLayout.chart.setDataList(chartUi.data)
        }
    }

    private fun showNoChartData() {
        binding.chartLayout.noData.visibility = View.VISIBLE
    }

}