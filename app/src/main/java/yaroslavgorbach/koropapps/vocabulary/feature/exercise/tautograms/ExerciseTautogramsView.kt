package yaroslavgorbach.koropapps.vocabulary.feature.exercise.tautograms

import yaroslavgorbach.koropapps.vocabulary.business.exercise.model.Letter
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

    fun setLetter(letter: Letter){
        bing.letter.text = bing.getString(letter.letter)
    }
}