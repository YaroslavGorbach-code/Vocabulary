package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.ui

import android.annotation.SuppressLint
import android.os.SystemClock
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentImageExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class ImageExerciseView(
    private val binding: FragmentImageExerciseBinding,
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

    fun setDescriptionText(text: String) {
        binding.descriptionText.text = text
    }

    fun setImageUrl(url: String) {
        Glide.with(binding.root).load(url).into(binding.image);
    }

    fun setExercise(exercise: TrainingExerciseUi) {
        if (exercise.isFinished) {
            callback.onBack()
        }

        setAimAndPerformed(exercise.aim, exercise.performed, exercise.progress.toFloat())
    }

    fun setIsRecording(isRecording: Boolean) {
        setRecordingButtonIcon(isRecording)
    }

    fun showRecordSavedSnack() {
        Snackbar.make(binding.root, R.string.record_saved, Snackbar.LENGTH_SHORT).show()
    }

    private fun setRecordingButtonIcon(isRecording: Boolean) {
        Log.i("dasdf", "view " + isRecording.toString())
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

    @SuppressLint("SetTextI18n")
    private fun setAimAndPerformed(aim: Int, performed: Int, progress: Float) {
        binding.performedAndAnim.root.visibility = View.VISIBLE
        binding.performedAndAnim.performedAnim.text = "$performed/$aim"
        binding.performedAndAnim.progress.progress = progress
    }
}