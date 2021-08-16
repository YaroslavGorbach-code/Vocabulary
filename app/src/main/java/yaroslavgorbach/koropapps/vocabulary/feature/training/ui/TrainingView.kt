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
    }

    private val listAdapter = TrainingExercisesListAdapter(callback::onExercise)

    init {
        initView()
    }

    private fun initView() {
        binding.list.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(LineDecorator(context, R.drawable.line_devider))
        }
    }

    fun setTrainingWitExercises(trainingWithExercisesUi: TrainingWithExercisesUi) {
        listAdapter.setData(trainingWithExercisesUi.exercises)
        binding.trainingProgress.setProgress(trainingWithExercisesUi.progress)
    }
}