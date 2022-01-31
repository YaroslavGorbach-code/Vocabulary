package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui

import android.annotation.SuppressLint
import android.os.SystemClock
import android.view.View
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseWithStagesBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToMaxLinesMapper
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model.StageUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class WordWithStageExerciseView(
    private val binding: FragmentExerciseWithStagesBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNextStage()
        fun onBack()
    }

    init {
        initEvents()
    }

    private fun initEvents() {
        binding.next.setOnClickListener {
            callback.onNextStage()
        }

        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }

        startAllWordsChronometer()
        startOneWordChronometer()
    }

    private fun formatWordLines(exerciseName: ExerciseName) {
        binding.cardText.word.maxLines = ExerciseNameToMaxLinesMapper().map(exerciseName)
    }

    @SuppressLint("SetTextI18n")
    fun setCurrentStage(stageUi: StageUi) {
        binding.stageTask.performedAnim.text = "${stageUi.number}/${stageUi.numberOfAllStages}"
        binding.stageTask.task.text = stageUi.text
    }

    fun setWord(word: String) {
        binding.cardText.word.text = word
        startOneWordChronometer()
    }

    fun setExercise(exercise: TrainingExerciseUi) {
        if (exercise.isFinished) {
            callback.onBack()
        }

        setAimAndPerformed(exercise.aim, exercise.performed, exercise.progress.toFloat())
    }

    fun setExerciseName(name: ExerciseName) {
        binding.toolbar.title = binding.getString(name.id)
        formatWordLines(name)
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