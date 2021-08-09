package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.ExerciseTrainingUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler.TrainingExercisesListAdapter

class TrainingView(
    private val binding: FragmentTrainingBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(exercise: ExerciseTrainingUi)
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


    fun setExercises(list: List<ExerciseTrainingUi>) {
        listAdapter.setData(list)
    }
}