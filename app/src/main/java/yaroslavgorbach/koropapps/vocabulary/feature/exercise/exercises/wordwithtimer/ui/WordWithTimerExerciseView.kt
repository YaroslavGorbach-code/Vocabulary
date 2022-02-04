package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.ui

import android.animation.ObjectAnimator
import android.view.View
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseWithTimerBinding
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class WordWithTimerExerciseView(
    private val binding: FragmentExerciseWithTimerBinding,
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

    private var exerciseName: ExerciseName? = null

    private fun initActions() {
        binding.root.setOnClickListener {
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
        exerciseName = name
    }

    fun setWord(word: String) {
        when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES, ExerciseName.ALPHABET_NOUN, ExerciseName.ALPHABET_VERBS -> {
                binding.progress.setText(word)
            }
            ExerciseName.DICTIONARY_ADJECTIVES, ExerciseName.DICTIONARY_NOUN, ExerciseName.DICTIONARY_VERBS -> {
                binding.numberOfWords.setText(word)
                binding.numberOfWords.visibility = View.VISIBLE
            }
            else -> {
            }
        }
    }

    fun setTimerState(state: Timer.State) {
        when (state) {
            Timer.State.Finish -> callback.onTimeEnd()
            is Timer.State.Tick -> {
                when (exerciseName) {
                    ExerciseName.DICTIONARY_ADJECTIVES, ExerciseName.DICTIONARY_NOUN, ExerciseName.DICTIONARY_VERBS -> {
                        binding.progress.setText((state.millisecondsUntilFinished / 1000).toString())
                    }
                    else -> {
                    }
                }
                binding.progress.setProgress(
                    ((((state.millisecondsUntilFinished / state.duration.toFloat()) * 100).toInt()))
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