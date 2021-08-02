package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding

class ExerciseView(
    private val binding: FragmentExerciseBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNext()
    }

    init {
        initEvents()
    }

    private fun initEvents() {
        binding.next.button.setOnClickListener {
            callback.onNext()
        }
    }

    fun setDescriptionText(text: String) {
        binding.description.descriptionText = text
    }

    fun setWord(word: String) {
        binding.word.letter.setLetter(word)
    }
}