package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.App
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation.ExercisesListViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.host
import javax.inject.Inject

@InternalCoroutinesApi
class ExercisesListFragment : Fragment(R.layout.fragment_exercises_list) {

    interface Router {
        fun openDescription(exercise: ExerciseUi)
        fun openTraining()
    }

    companion object {
        fun newInstance() = ExercisesListFragment()
    }

    private lateinit var exercisesView: ExercisesListView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ExercisesListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initDagger() {
        (requireActivity().application as App).appComponent
            .exercisesListComponent()
            .create()
            .inject(this)
    }

    private fun initObservers() {
        viewModel.exercises.observe(viewLifecycleOwner, exercisesView::setExercises)

        viewModel.training.observe(viewLifecycleOwner, exercisesView::setTraining)
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