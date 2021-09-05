package yaroslavgorbach.koropapps.vocabulary.feature.profile.ui

import android.view.View
import im.dacer.androidcharts.LineView
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentProfileBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.model.ChartDayUi

class ProfileView(private val binding: FragmentProfileBinding, private val callback: Callback) {

    interface Callback {
        fun onNextChart()
        fun onPreviousChart()
    }

    init {
        initActions()
    }

    private fun initActions() {
        binding.chart.prevData.setOnClickListener {
            callback.onPreviousChart()
        }

        binding.chart.nextData.setOnClickListener {
            callback.onNextChart()
        }
    }

    fun setChart(chartDayUi: ChartDayUi) {
        if (chartDayUi.isEmpty) {
            showNoChartValueData()
        } else {
            binding.chart.chart.setDrawDotLine(false)
            binding.chart.chart.setShowPopup(LineView.SHOW_POPUPS_All)
            binding.chart.chart.setBottomTextList(chartDayUi.labels)
            binding.chart.chart.setColorArray(chartDayUi.getColors(binding.root.context))
            binding.chart.chart.setDataList(chartDayUi.data)
        }
    }

    private fun showNoChartValueData() {
        binding.chart.noData.visibility = View.VISIBLE
        binding.chart.chart.visibility = View.GONE
    }
}