package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises

import android.os.SystemClock
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.utils.getString

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
            startOneWordChronometer()
        }
        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }
        startAllWordsChronometer()
        startOneWordChronometer()
    }

    fun setDescriptionText(text: String) {
        binding.descriptionText.text = text
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

    private fun startOneWordChronometer() {
        binding.chronometerOneWord.base = SystemClock.elapsedRealtime()
        binding.chronometerOneWord.start()
    }

    private fun startAllWordsChronometer() {
        binding.chronometer.start()
    }
}