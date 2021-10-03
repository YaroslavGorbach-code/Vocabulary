package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.dictionary.ui

import android.animation.ObjectAnimator
import android.view.View
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseDictionaryBinding
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class DictionaryView(
    private val binding: FragmentExerciseDictionaryBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onClick()
        fun onTimeEnd()
        fun onBack()
    }

    init {
        initActions()
        startClickHelperAnimation()
    }

    private fun initActions() {
        binding.clickSurface.setOnClickListener {
            callback.onClick()
            stopClickHelperAnimation()
        }

        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }
    }

    fun setDescriptionText(text: String) {
        binding.descriptionText.text = text
    }

    fun setExerciseName(name: ExerciseName) {
        binding.toolbar.title = binding.getString(name.id)
    }

    fun setNumberOfClicked(numberOfClicked: Int) {
        binding.numberOfWords.setText(numberOfClicked.toString())
    }

    fun setTimerState(state: Timer.State) {
        when (state) {
            Timer.State.Finish -> callback.onTimeEnd()
            is Timer.State.Tick -> {
                binding.progress.setProgress(
                    ((((state.millisecondsUntilFinished / Timer.ONE_MINUTE.toFloat()) * 100).toInt()))
                )
                binding.progress.setText((state.millisecondsUntilFinished / 1000).toString())
            }
        }
    }

    private fun startClickHelperAnimation() {
        animateHelper(binding.clickHelperIcon)
        animateHelper(binding.clickHelperText)
    }

    private fun stopClickHelperAnimation() {
        binding.clickHelperIcon.visibility = View.GONE
        binding.clickHelperText.visibility = View.GONE
    }

    private fun animateHelper(view: View) {
        ObjectAnimator().apply {
            target = view
            setAutoCancel(false)
            setPropertyName(View.TRANSLATION_Y.name)
            setFloatValues(100f)
            duration = 1000
            repeatCount = 5
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }
}