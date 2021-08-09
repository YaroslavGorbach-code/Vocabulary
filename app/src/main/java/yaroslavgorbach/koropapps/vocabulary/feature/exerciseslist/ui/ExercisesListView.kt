package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler.ExercisesListAdapter

class ExercisesListView(
    private val binding: FragmentExercisesListBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(exercise: ExerciseUi)
        fun onTraining()
    }

    private val listAdapter = ExercisesListAdapter(callback::onExercise)

    init {
        initView()
        initActions()
    }

    private fun initView() {
        binding.exercisesList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(LineDecorator(this.context, R.drawable.line_devider))
        }
    }

    private fun initActions() {
        binding.training.item.setOnClickListener { callback.onTraining() }
    }

    fun setExercises(list: List<ExerciseUi>) {
        listAdapter.setData(list)
    }
}