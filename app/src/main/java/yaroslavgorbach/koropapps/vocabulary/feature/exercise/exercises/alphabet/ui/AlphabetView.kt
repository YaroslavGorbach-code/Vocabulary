package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui

import android.animation.ObjectAnimator
import android.view.View
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAlphabetBinding
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer.Companion.ONE_SECOND
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class AlphabetView(
    private val binding: FragmentExerciseAlphabetBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNewLetter()
        fun onTimeEnd()
        fun onGameFinished()
        fun onBack()
    }

    init {
        initActions()
        startClickHelperAnimation()
    }

    private fun initActions() {
        binding.clickSurface.setOnClickListener {
            callback.onNewLetter()
            stopClickHelperAnimation()
        }

        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }
    }

    fun setLetter(letter: String?) {
        if (letter != null) {
            binding.letterProgress.setText(letter)
        } else {
            callback.onGameFinished()
        }
    }

    fun setDescriptionText(text: String) {
        binding.descriptionText.text = text
    }

    fun setExerciseName(name: ExerciseName) {
        binding.toolbar.title = binding.getString(name.id)
    }

    fun setTimerState(state: Timer.State) {
        when (state) {
            Timer.State.Finish -> callback.onTimeEnd()
            is Timer.State.Tick -> {
                binding.letterProgress.setProgress(
                    ((((state.millisecondsUntilFinished / (ONE_SECOND * 5).toFloat()) * 100).toInt()))
                )
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