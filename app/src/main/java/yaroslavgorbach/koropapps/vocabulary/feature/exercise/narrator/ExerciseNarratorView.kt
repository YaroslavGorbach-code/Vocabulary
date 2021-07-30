package yaroslavgorbach.koropapps.vocabulary.feature.exercise.narrator

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseNarratorBinding

class ExerciseNarratorView(
    private val binding: FragmentExerciseNarratorBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onNext()
    }

    init {
        binding.next.button.setOnClickListener {
            callback.onNext()
        }
    }

    fun setLetter(text: String) {
        binding.letter.letter.setLetter(text)
    }

    fun setDescriptionText(descriptionText: String) {
        binding.description.descriptionText = descriptionText
    }

}