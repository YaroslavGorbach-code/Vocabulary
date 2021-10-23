package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentLevelBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi

class LevelView(private val binding: FragmentLevelBinding, private val callback: Callback) {

    interface Callback {
        fun onBack()
    }

    init {
        initActions()
    }

    private fun initActions() {
//        binding.close.setOnClickListener {
//            callback.onBack()
//        }
    }

    fun setLevelInfo(levelInfoUi: LevelInfoUi) {
//        binding.level.text = levelInfoUi.level.level
//        binding.levelProgress.progress = levelInfoUi.progress
//
//        with(binding.summaryTrainingTime) {
//            icon.setImageResource(R.drawable.ic_time)
//            text.text = getString(R.string.summary_training_time)
//            value.text = levelInfoUi.summaryTrainingTime
//        }
//
//        with(binding.performedDailyTrainings) {
//            icon.setImageResource(R.drawable.ic_rocket_black)
//            text.text = getString(R.string.completed_daily_trainings)
//            value.text = levelInfoUi.completedDailyTrainings
//        }
//
//        with(binding.performedExercises) {
//            icon.setImageResource(R.drawable.ic_exercise)
//            text.text = getString(R.string.completed_exercises)
//            value.text = levelInfoUi.completedExercises
//        }
    }
}