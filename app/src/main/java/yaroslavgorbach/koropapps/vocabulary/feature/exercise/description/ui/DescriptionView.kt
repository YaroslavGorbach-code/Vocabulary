package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.view.View
import androidx.core.widget.NestedScrollView
import im.dacer.androidcharts.LineView
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
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
        binding.chartValue.nextData.setOnClickListener {
            callback.onNextChartValue()
        }
        binding.chartValue.prevData.setOnClickListener {
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

    fun setDescription(description: Description) {
        binding.descriptionText.text = binding.getString(description.textRes)
        with(binding.getDrawable(description.exerciseIconRes)) {
            binding.icon5.setImageDrawable(this)
        }
        binding.toolbar.title = binding.getString(description.exerciseName.id)
    }

    fun setChartValue(chartValueUi: ChartValueUi) {
        if (chartValueUi.isEmpty) {
            showNoChartValueData()
        } else {
            binding.chartValue.chartValue.setDrawDotLine(false)
            binding.chartValue.chartValue.setShowPopup(LineView.SHOW_POPUPS_All)
            binding.chartValue.chartValue.setBottomTextList(chartValueUi.labels)
            binding.chartValue.chartValue.setColorArray(chartValueUi.getColors(binding.root.context))
            binding.chartValue.chartValue.setDataList(chartValueUi.data)
        }
    }

    private fun showNoChartValueData() {
        binding.chartValue.noData.visibility = View.VISIBLE
        binding.chartValue.chartValue.visibility = View.GONE
    }

    fun setChartTime(chartTimeUi: ChartTimeUi) {
        if (chartTimeUi.isEmpty) {
            showNoChartTimeData()
        } else {
            binding.chartTime.chartValue.setDrawDotLine(false)
            binding.chartTime.chartValue.setShowPopup(LineView.SHOW_POPUPS_All)
            binding.chartTime.chartValue.setBottomTextList(chartTimeUi.labels)
            binding.chartTime.chartValue.setColorArray(chartTimeUi.getColors(binding.root.context))
            binding.chartTime.chartValue.setDataList(chartTimeUi.data)
        }
    }

    private fun showNoChartTimeData() {
        binding.chartTime.noData.visibility = View.VISIBLE
        binding.chartTime.chartValue.visibility = View.GONE
    }

}