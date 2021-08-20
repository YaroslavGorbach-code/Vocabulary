package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAlphabetBinding

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
    }

    private fun initActions() {
        binding.clickSurface.setOnClickListener {
            callback.onNewLetter()
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

    fun setExerciseName(name: String) {
        binding.toolbar.title = name
    }

    fun setProgress(progress: Int) {
        if (progress == 100) {
            callback.onTimeEnd()
        }
        binding.letterProgress.setProgress(progress)
    }
}