package yaroslavgorbach.koropapps.vocabulary.feature.exercise.rememberall

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseRememberAllBinding

class ExerciseRememberAllView(
    private val binding: FragmentExerciseRememberAllBinding,
    callback: Callback
) {
    interface Callback {
        fun onNext()
    }

    init {
        binding.next.setOnClickListener {
            callback.onNext()
        }
    }

    fun setText(text: String){
        binding.description.text = text
    }
}