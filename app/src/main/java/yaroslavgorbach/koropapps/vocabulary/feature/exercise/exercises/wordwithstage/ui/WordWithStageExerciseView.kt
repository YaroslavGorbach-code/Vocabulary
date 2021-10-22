package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui

import android.os.SystemClock
import android.util.Log
import android.view.View
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseWithStagesBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model.StageUi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui.recycler.StagesAdapter
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

    private var stagesAdapter: StagesAdapter? = null

    init {
        initViews()
        initEvents()
    }

    private fun initViews() {
        stagesAdapter = StagesAdapter()

        binding.stages.adapter = stagesAdapter
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

    fun setStages(stageUi: List<StageUi>) {
        stagesAdapter?.submitList(stageUi.toMutableList())
    }

    fun setWord(word: String) {
        binding.cardText.word.text = word
        startOneWordChronometer()
    }

    fun setExercise(exercise: TrainingExerciseUi) {
        if (exercise.isFinished) {
            callback.onBack()
        }

        setAimAndPerformed(exercise.aim, exercise.performed)
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