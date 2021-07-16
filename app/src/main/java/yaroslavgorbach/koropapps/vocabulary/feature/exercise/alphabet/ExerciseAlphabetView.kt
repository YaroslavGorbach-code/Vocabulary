package yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAlphabetBinding

class ExerciseAlphabetView(
    private val binding: FragmentExerciseAlphabetBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNewLetter()
    }


    fun setLetter(letter: String) {
        binding.letterProgress.setLetter(letter)
    }

    fun descriptionText(text: String) {
        binding.text.text = text
    }

    fun setProgress(progress: Int){
        if (progress==100){
            callback.onNewLetter()
        }
        binding.letterProgress.setProgress(progress)
    }
}