package yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.ui

import android.view.View
import androidx.core.view.isGone
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentProfileBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.model.ChartDayUi
import yaroslavgorbach.koropapps.vocabulary.utils.feature.line_view.LineView
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
            icon.setImageDrawable(getDrawable(R.drawable.ic_remove_ads))
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

        binding.level.root.setOnClickListener {
            callback.onLevel()
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
        binding.phraseName.text = phrase.phrase
        binding.phraseText.text = phrase.explanation
    }

    fun isRemoveAdFeatureVisible(isVisible: Boolean){
        binding.removeAds.root.isGone = isVisible.not()
    }

    private fun showNoChartValueData() {
        binding.chart.noData.visibility = View.VISIBLE
        binding.chart.chart.visibility = View.GONE
    }

    fun setLevel(levelInfoUi: LevelInfoUi) {
        binding.level.levelProgress.setProgress(levelInfoUi.oratorLevelProgress)
    }
}