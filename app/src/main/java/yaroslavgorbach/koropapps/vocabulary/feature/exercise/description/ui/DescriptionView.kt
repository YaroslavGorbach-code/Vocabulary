package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.view.View
import androidx.core.widget.NestedScrollView
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
        binding.chart.nextData.setOnClickListener {
            callback.onNextChart()
        }
        binding.chart.prevData.setOnClickListener {
            callback.onPreviousChart()
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

    fun setDescription(description: Description) {
        binding.descriptionText.text = binding.getString(description.textRes)
        with(binding.getDrawable(description.exerciseIconRes)) {
            binding.icon5.setImageDrawable(this)
        }
        binding.toolbar.title = binding.getString(description.exerciseName.id)
    }

    fun setChart(chartUi: ChartUi) {
        if (chartUi.isEmpty) {
            showNoChartData()
        } else {
            binding.chart.chart.setDrawDotLine(false)
            binding.chart.chart.setShowPopup(LineView.SHOW_POPUPS_All)
            binding.chart.chart.setBottomTextList(chartUi.labels)
            binding.chart.chart.setColorArray(chartUi.getColors(binding.root.context))
            binding.chart.chart.setDataList(chartUi.data)
        }
    }

    private fun showNoChartData() {
        binding.chart.noData.visibility = View.VISIBLE
        binding.chart.chart.visibility = View.GONE
    }

}