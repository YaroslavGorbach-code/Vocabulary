package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui

import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.base.LineDecorator

class ExercisesListView(
    private val binding: FragmentExercisesListBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(exercise: Exercise)
    }

    private val listAdapter = ExercisesListAdapter {exercise->
        callback.onExercise(exercise)
    }

    init {
        binding.exercisesList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(LineDecorator(this.context, R.drawable.line_devider))
        }
    }

    fun setExercises(list: List<Exercise>) {
        listAdapter.setData(list)
    }
}