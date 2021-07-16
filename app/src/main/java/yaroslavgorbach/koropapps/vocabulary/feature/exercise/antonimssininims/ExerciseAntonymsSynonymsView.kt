package yaroslavgorbach.koropapps.vocabulary.feature.exercise.antonimssininims

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentAntonymsSynonymsBinding

class ExerciseAntonymsSynonymsView(
    private val binding: FragmentAntonymsSynonymsBinding,
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

    fun setTaskText(text: String) {
        binding.description.text = text
    }
}