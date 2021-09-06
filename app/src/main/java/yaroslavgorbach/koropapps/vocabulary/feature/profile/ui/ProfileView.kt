package yaroslavgorbach.koropapps.vocabulary.feature.profile.ui

import android.view.View
import im.dacer.androidcharts.LineView
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentProfileBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.model.ChartDayUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable

class ProfileView(private val binding: FragmentProfileBinding, private val callback: Callback) {

    interface Callback {
        fun onNextChart()
        fun onPreviousChart()
        fun onSettings()
        fun onRemoveAds()
        fun onShare()
        fun onRate()
    }

    init {
        initViews()
        initActions()
    }

    private fun initViews() {
        with(binding.settings) {
            icon.setImageDrawable(getDrawable(R.drawable.ic_settings))
            text.setText(R.string.settings)
        }

        with(binding.removeAds) {
            icon.setImageDrawable(getDrawable(R.drawable.ic_no_ads))
            text.setText(R.string.remove_ads)
        }

        with(binding.rate) {
            icon.setImageDrawable(getDrawable(R.drawable.ic_rate))
            text.setText(R.string.rate_app)
        }

        with(binding.share) {
            icon.setImageDrawable(getDrawable(R.drawable.ic_share))
            text.setText(R.string.share_app)
        }
    }

    private fun initActions() {
        binding.chart.prevData.setOnClickListener {
            callback.onPreviousChart()
        }

        binding.chart.nextData.setOnClickListener {
            callback.onNextChart()
        }

        binding.rate.item.setOnClickListener {
            callback.onRate()
        }

        binding.removeAds.item.setOnClickListener {
            callback.onRemoveAds()
        }

        binding.share.item.setOnClickListener {
            callback.onShare()
        }

        binding.settings.item.setOnClickListener {
            callback.onSettings()
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