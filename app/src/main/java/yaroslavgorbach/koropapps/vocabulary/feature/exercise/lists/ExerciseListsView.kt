package yaroslavgorbach.koropapps.vocabulary.feature.exercise.lists

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseListsBinding

class ExerciseListsView(private val binding: FragmentExerciseListsBinding, callback: Callback) {
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