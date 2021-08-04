package yaroslavgorbach.koropapps.vocabulary.feature.exercise

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.util.getString

class ExerciseView(
    private val binding: FragmentExerciseBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNext()
        fun onBack()
    }

    init {
        initEvents()
    }

    private fun initEvents() {
        binding.next.setOnClickListener {
            callback.onNext()
        }
        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }
    }

    fun setDescriptionText(text: String) {
        binding.description.text = text
    }

    fun setShortDescriptionText(text: String) {
        binding.letter.setDescription(text)
    }

    fun setWord(word: String) {
        binding.letter.setLetter(word)
    }

    fun setExerciseName(name: ExerciseName) {
        binding.toolbar.title = binding.getString(name.id)
    }
}