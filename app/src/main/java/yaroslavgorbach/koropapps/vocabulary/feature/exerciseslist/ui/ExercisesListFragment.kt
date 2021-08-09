package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation.ExercisesListVm
import yaroslavgorbach.koropapps.vocabulary.util.host

@InternalCoroutinesApi
class ExercisesListFragment : Fragment(R.layout.fragment_exercises_list) {

    interface Router {
        fun openDescription(exercise: Exercise)
        fun openTraining()
    }

    private lateinit var exercisesView: ExercisesListView

    private val viewModel by viewModels<ExercisesListVm>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.getExercises().collect(object : FlowCollector<List<Exercise>> {
                override suspend fun emit(value: List<Exercise>) {
                    exercisesView.setExercises(value)
                }
            })
        }
    }

    private fun initView() {
        exercisesView = ExercisesListView(
            FragmentExercisesListBinding.bind(requireView()), object : ExercisesListView.Callback {
                override fun onExercise(exercise: Exercise) {
                    host<Router>().openDescription(exercise)
                }

                override fun onTraining() {
                    host<Router>().openTraining()
                }
            })
    }
}