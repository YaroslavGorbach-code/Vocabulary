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
        binding.button.setOnClickListener {
            callback.onNext()
        }
    }

    fun setText(text: String) {
        binding.text.text = text
    }

}