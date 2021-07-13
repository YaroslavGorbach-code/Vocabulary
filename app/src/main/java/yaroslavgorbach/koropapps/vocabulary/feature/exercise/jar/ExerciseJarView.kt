package yaroslavgorbach.koropapps.vocabulary.feature.exercise.jar

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseJarBinding

class ExerciseJarView(
    private val binding: FragmentExerciseJarBinding,
    private val callback: Callback
) {
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