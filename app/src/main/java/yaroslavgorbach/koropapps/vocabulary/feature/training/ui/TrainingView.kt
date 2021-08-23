package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler.TrainingExercisesListAdapter

class TrainingView(
    private val binding: FragmentTrainingBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(withExercises: TrainingExerciseUi)
        fun onBack()
    }

    private val listAdapter = TrainingExercisesListAdapter(callback::onExercise)

    init {
        initView()
        initActions()
    }

    private fun initView() {
        binding.list.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(LineDecorator(context, R.drawable.line_devider))
        }
    }

    private fun initActions() {
        binding.close.setOnClickListener {
            callback.onBack()
        }
    }

    fun setTrainingWitExercises(trainingWithExercisesUi: TrainingWithExercisesUi) {
        listAdapter.setData(trainingWithExercisesUi.exercises)
        binding.trainingProgress.progress = trainingWithExercisesUi.progress
    }
}