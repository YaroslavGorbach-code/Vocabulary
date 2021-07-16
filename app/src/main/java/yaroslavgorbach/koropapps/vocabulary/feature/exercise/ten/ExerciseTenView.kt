package yaroslavgorbach.koropapps.vocabulary.feature.exercise.ten

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseTenBinding

class ExerciseTenView(private val binding: FragmentExerciseTenBinding, callback: Callback) {
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