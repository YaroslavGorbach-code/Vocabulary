package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui

import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.data.vocabulary.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.databinding.ExercisesListFragmentBinding

class ExercisesListView(private val binding: ExercisesListFragmentBinding) {
    private val listAdapter = ExercisesListAdapter()
    init {
        binding.exercisesList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }
    }

    fun setExercises(list: List<Exercise>){
        listAdapter.setData(list)
    }
}