package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler.TrainingExercisesListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.formatDefault
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class TrainingView(
    private val binding: FragmentTrainingBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(exercise: TrainingExerciseUi)
        fun onShowTrainingIsFinishedDialog()
        fun onBack()
    }

    private val adapter = TrainingExercisesListAdapter(callback::onExercise)

    init {
        initRecycler()
        initActions()
    }

    @SuppressLint("SetTextI18n")
    fun setTrainingWitExercises(trainingWithExercisesUi: TrainingWithExercisesUi) {
        adapter.setData(trainingWithExercisesUi.exercises)

        if (trainingWithExercisesUi.isTrainingFinished) {
            callback.onShowTrainingIsFinishedDialog()
        }

        trainingWithExercisesUi.lastTrainingDate?.let { date ->
            binding.date.text =
                binding.getString(R.string.last_training_was) + " " + date.formatDefault()
        }

        binding.daysInRow.text =
            binding.getString(R.string.finished_trainings_in_row) + " " + trainingWithExercisesUi.daysWithoutInterruption
    }

    private fun initRecycler() {
        val divider =
            MaterialDividerItemDecoration(binding.root.context, LinearLayoutManager.VERTICAL)
        divider.dividerInsetStart = 160
        binding.list.addItemDecoration(divider)
        binding.list.adapter = adapter
    }

    private fun initActions() {
        binding.icClose.setOnClickListener {
            callback.onBack()
        }
    }
}