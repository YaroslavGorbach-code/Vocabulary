package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation.ExercisesListViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.util.host

@InternalCoroutinesApi
class ExercisesListFragment : Fragment(R.layout.fragment_exercises_list) {

    interface Router {
        fun openDescription(exercise: ExerciseUi)
        fun openTraining()
    }

    private lateinit var exercisesView: ExercisesListView

    private val viewModel by viewModels<ExercisesListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.exercises.observe(viewLifecycleOwner, exercisesView::setExercises)
    }


    private fun initView() {
        exercisesView = ExercisesListView(
            FragmentExercisesListBinding.bind(requireView()), object : ExercisesListView.Callback {
                override fun onExercise(exercise: ExerciseUi) {
                    host<Router>().openDescription(exercise)
                }

                override fun onTraining() {
                    host<Router>().openTraining()
                }
            })
    }
}