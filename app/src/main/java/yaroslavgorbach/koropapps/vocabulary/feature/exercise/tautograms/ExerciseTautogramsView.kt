package yaroslavgorbach.koropapps.vocabulary.feature.exercise.tautograms

import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTautogramsExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.util.getString

class ExerciseTautogramsView(
    private val bing: FragmentTautogramsExerciseBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNextLetter()
    }

    init {
        bing.buttonNext.setOnClickListener {
            callback.onNextLetter()
        }
    }

    fun setLetter(letter: String){
        bing.letter.text = letter
    }

    fun setText(text: String){
        bing.description.text = text
    }
}