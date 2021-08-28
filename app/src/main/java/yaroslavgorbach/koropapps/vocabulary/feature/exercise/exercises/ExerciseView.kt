package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises

import android.os.SystemClock
import android.view.View
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
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
        binding.letter.setAtTop(text)
    }

    fun setWord(word: String) {
        binding.letter.setText(word)
    }

    fun setExercise(exercise: TrainingExerciseEntity) {
        if (exercise.isFinished) {
            callback.onBack()
        }
        setAimAndPerformed(exercise.aim, exercise.performed)
        setExerciseName(exercise.name)
    }

    private fun startOneWordChronometer() {
        binding.chronometerOneWord.base = SystemClock.elapsedRealtime()
        binding.chronometerOneWord.start()
    }

    private fun startAllWordsChronometer() {
        binding.chronometer.start()
    }

    private fun setExerciseName(name: ExerciseName) {
        binding.toolbar.title = binding.getString(name.id)
    }

    private fun setAimAndPerformed(aim: Int, performed: Int) {
        binding.aimAndPerformed.visibility = View.VISIBLE
        binding.aimAndPerformed.setText("$performed/$aim")
    }

}