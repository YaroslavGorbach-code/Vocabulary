package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentLevelBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui.recycler.AchievementsAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable

class LevelView(private val binding: FragmentLevelBinding, private val callback: Callback) {

    interface Callback {
        fun onBack()
        fun onAchievement(achievement: Achievement)
    }

    private var achievementsAdapter: AchievementsAdapter? = null

    init {
        initViews()
        initActions()
    }

    private fun initViews() {
        achievementsAdapter = AchievementsAdapter(callback::onAchievement)

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

    fun setLevelInfo(levelInfoUi: LevelInfoUi) {
        binding.levels.level.setText(levelInfoUi.oratorLevel.level)
        binding.levels.level.setProgress(levelInfoUi.oratorLevelProgress)

        binding.levels.levelDictionImageProgress.setProgress(levelInfoUi.dictionLevelProgress)
        binding.levels.levelDictionProgressText.text = levelInfoUi.dictionLevel.level

        binding.levels.levelImprovisationImageProgress.setProgress(levelInfoUi.communicationLevelProgress)
        binding.levels.levelImprovisationProgressText.text = levelInfoUi.communicationLevel.level

        binding.levels.levelVocabularyImageProgress.setProgress(levelInfoUi.vocabularyLevelProgress)
        binding.levels.levelVocabularyProgressText.text = levelInfoUi.vocabularyLevel.level
    }

    fun setAchievements(achievements: List<Achievement>) {
        achievementsAdapter?.let { it.items = achievements }
    }
}