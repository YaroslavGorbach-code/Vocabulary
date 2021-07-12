package yaroslavgorbach.koropapps.vocabulary.feature.exercise.associations

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAssociationsBinding

class ExerciseAssociationsView(
    private val binding: FragmentExerciseAssociationsBinding,
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
        binding.text.text = text
    }

}