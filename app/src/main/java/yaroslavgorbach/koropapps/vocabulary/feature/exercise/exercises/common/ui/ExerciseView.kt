package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.common.ui

import android.os.SystemClock
import android.view.View
import com.google.android.material.snackbar.Snackbar
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class ExerciseView(
    private val binding: FragmentExerciseBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNext()
        fun onBack()
        fun onStartStopRecording()
    }

    init {
        initEvents()
        startAllWordsChronometer()
        startOneWordChronometer()
    }

    private fun initEvents() {
        binding.next.setOnClickListener {
            callback.onNext()
            startOneWordChronometer()
        }

        binding.startStopRecord.setOnClickListener {
            callback.onStartStopRecording()
        }

        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }
    }

    private fun setSingleLineWordTextViewMod(word: String) {
        if (word.length < 30) {
            binding.cardText.word.maxLines = 1
        } else {
            binding.cardText.word.maxLines = Int.MAX_VALUE
        }
    }

    fun setDescriptionText(text: String) {
        binding.descriptionText.text = text
    }

    fun setShortDescriptionAboveWord(text: String) {
        binding.cardText.textAtTop.visibility = View.VISIBLE

        binding.cardText.textAtTop.text = text
    }

    fun setWord(word: String) {
        setSingleLineWordTextViewMod(word)
        binding.cardText.word.text = word
    }


    fun setExercise(exercise: TrainingExerciseUi) {
        if (exercise.isFinished) {
            callback.onBack()
        }

        setAimAndPerformed(exercise.aim, exercise.performed)
    }

    fun setIsRecording(isRecording: Boolean) {
        setRecordingButtonIcon(isRecording)
    }

    fun showRecordSavedSnack() {
        Snackbar.make(binding.root, R.string.record_saved, Snackbar.LENGTH_SHORT).show()
    }

    private fun setRecordingButtonIcon(isRecording: Boolean) {
        if (isRecording) {
            binding.startStopRecord.setImageResource(R.drawable.ic_voice_recording)
        } else {
            binding.startStopRecord.setImageResource(R.drawable.ic_voice_record_stop)

        }
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

    private fun setAimAndPerformed(aim: Int, performed: Int) {
        binding.aimAndPerformed.visibility = View.VISIBLE
        binding.aimAndPerformed.setText("$performed/$aim")
    }

}