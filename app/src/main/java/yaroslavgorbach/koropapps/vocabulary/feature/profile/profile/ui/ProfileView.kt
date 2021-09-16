package yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.ui

import android.view.View
import im.dacer.androidcharts.LineView
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentProfileBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.model.LevelInfoUi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.model.ChartDayUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable

class ProfileView(private val binding: FragmentProfileBinding, private val callback: Callback) {

    interface Callback {
        fun onNextChart()
        fun onPreviousChart()
        fun onSettings()
        fun onRemoveAds()
        fun onShare()
        fun onRate()
        fun onLevel()
        fun onPhrase(phrase: String)
    }

    private var phrase: Phrase? = null

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

        binding.levelCard.setOnClickListener {
            callback.onLevel()
        }

        binding.phrase.root.setOnClickListener {
            callback.onPhrase(phrase?.explanation ?: "")
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

    fun setPhrase(phrase: Phrase) {
        this.phrase = phrase
        binding.phrase.phrase.text = phrase.phrase
    }

    private fun showNoChartValueData() {
        binding.chart.noData.visibility = View.VISIBLE
        binding.chart.chart.visibility = View.GONE
    }

    fun setLevel(levelInfoUi: LevelInfoUi) {
        binding.level.levelProgress.setProgress(levelInfoUi.progress)
        binding.level.levelProgress.setText(levelInfoUi.level.level)
    }
}