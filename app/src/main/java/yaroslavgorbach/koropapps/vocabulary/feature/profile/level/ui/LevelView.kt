package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentLevelBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class LevelView(private val binding: FragmentLevelBinding, private val callback: Callback) {

    interface Callback {
        fun onBack()
    }

    init {
        initActions()
    }

    private fun initActions() {
        binding.close.setOnClickListener {
            callback.onBack()
        }
    }

    fun setLevelInfo(levelInfoUi: LevelInfoUi) {
        binding.level.text = levelInfoUi.level
        binding.levelProgress.progress = levelInfoUi.progress

        with(binding.summaryTrainingTime) {
            icon.setImageResource(R.drawable.ic_rocket)
            text.text = getString(R.string.summary_training_time)
            value.text = levelInfoUi.summaryTrainingTime
        }

        with(binding.performedDailyTrainings) {
            icon.setImageResource(R.drawable.ic_rocket)
            text.text = getString(R.string.performed_daily_trainings)
            value.text = levelInfoUi.performedDailyTrainings
        }

        with(binding.performedExercises) {
            icon.setImageResource(R.drawable.ic_rocket)
            text.text = getString(R.string.performed_exercises)
            value.text = levelInfoUi.performedExercises
        }
    }
}