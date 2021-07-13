package yaroslavgorbach.koropapps.vocabulary.feature.exercise.game

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseGameBinding

class ExerciseGameView(private val binding: FragmentExerciseGameBinding, callback: Callback) {
    interface Callback {
        fun onNext()
    }

    init {
        binding.next.setOnClickListener {
            callback.onNext()
        }
    }

    fun setText(text: String) {
        binding.text.text = text
    }
}