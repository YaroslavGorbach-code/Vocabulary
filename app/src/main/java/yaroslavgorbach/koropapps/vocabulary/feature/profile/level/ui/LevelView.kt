package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentLevelBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.OratorLevelInfoUi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui.recycler.AchievementsAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable

class LevelView(private val binding: FragmentLevelBinding, private val callback: Callback) {

    interface Callback {
        fun onBack()
    }

    private var achievementsAdapter: AchievementsAdapter? = null

    init {
        initViews()
        initActions()
    }

    private fun initViews() {
        achievementsAdapter = AchievementsAdapter()

        binding.achievements.listAchievements.apply {
            adapter = achievementsAdapter
            layoutManager = LinearLayoutManager(binding.root.context, HORIZONTAL, false)
        }

        binding.levels.levelImprovisationImageProgress.setImage(binding.getDrawable(R.drawable.ic_impromptu))

        binding.levels.levelDictionImageProgress.setImage(binding.getDrawable(R.drawable.ic_diction))

        binding.levels.levelVocabularyImageProgress.setImage(binding.getDrawable(R.drawable.ic_vocabulary))
    }

    private fun initActions() {
        binding.icClose.setOnClickListener {
            callback.onBack()
        }
    }

    fun setLevelInfo(oratorLevelInfoUi: OratorLevelInfoUi) {
        binding.levels.level.setText(oratorLevelInfoUi.level.level)
        binding.levels.level.setProgress(oratorLevelInfoUi.progress)
    }

    fun setAchievements(achievements: List<Achievement>) {
        Log.i("ffdxvx", achievements.toString())
        achievementsAdapter?.let { it.items = achievements }
    }
}