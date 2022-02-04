package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.common.ui

import android.annotation.SuppressLint
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.SystemClock
import android.view.View
import com.google.android.material.snackbar.Snackbar
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToMaxLinesMapper
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.animate
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

    fun setDescriptionText(text: String) {
        binding.descriptionText.text = text
    }

    fun setShortDescriptionAboveWord(text: String) {
        binding.cardText.textAtTop.visibility = View.VISIBLE
        binding.cardText.textAtTop.text = text
    }

    fun setWord(word: String) {
        binding.cardText.word.text = word
    }

    fun setExercise(exercise: TrainingExerciseUi) {
        if (exercise.isFinished) {
            callback.onBack()
        }

        setAimAndPerformed(exercise.aim, exercise.performed, exercise.progress.toFloat())
        setUpNextTaskButton(exercise.isLastTask)
    }

    private fun setUpNextTaskButton(lastTask: Boolean) {
        if (lastTask) {
            binding.next.drawable.animate()
        }
    }

    fun setIsRecording(isRecording: Boolean) {
        setRecordingButtonIcon(isRecording)
    }

    fun showRecordSavedSnack() {
        Snackbar.make(binding.root, R.string.record_saved, Snackbar.LENGTH_SHORT).show()
    }

    private fun setRecordingButtonIcon(isRecording: Boolean) {
        if (isRecording) {
            binding.startStopRecord.setImageResource(R.drawable.anim_micro_to_active)
            binding.startStopRecord.drawable.animate()
        } else {
            binding.startStopRecord.setImageResource(R.drawable.anim_micro_to_not_active)
            binding.startStopRecord.drawable.animate()
        }
    }

    fun setExerciseName(name: ExerciseName) {
        setWordDescription(name)
        formatWordLines(name)
        binding.toolbar.title = binding.getString(name.id)
    }

    private fun formatWordLines(exerciseName: ExerciseName) {
        binding.cardText.word.maxLines = ExerciseNameToMaxLinesMapper().map(exerciseName)
    }

    private fun startOneWordChronometer() {
        binding.chronometerOneWord.base = SystemClock.elapsedRealtime()
        binding.chronometerOneWord.start()
    }

    private fun startAllWordsChronometer() {
        binding.chronometer.start()
    }

    @SuppressLint("SetTextI18n")
    private fun setAimAndPerformed(aim: Int, performed: Int, process: Float) {
        binding.performedAndAnim.root.visibility = View.VISIBLE
        binding.performedAndAnim.performedAnim.text = "$performed / $aim"
        binding.performedAndAnim.progress.progress = process
    }

    private fun setWordDescription(name: ExerciseName) {
        binding.cardText.textAtTop.visibility = View.VISIBLE

        binding.cardText.textAtTop.text = when (name) {
            ExerciseName.NARRATOR_ADJECTIVES, ExerciseName.NARRATOR_NOUN, ExerciseName.NARRATOR_VERBS -> {
                binding.getString(R.string.number_of_words_in_story)
            }
            else -> ""
        }
    }
}