package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.App
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation.DescriptionViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.utils.host
import javax.inject.Inject

class DescriptionFragment : Fragment(R.layout.fragment_description) {

    interface Router {
        fun onOpenExercise(exerciseType: ExerciseType)
    }

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = DescriptionFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DescriptionViewModel> { viewModelFactory }

    private lateinit var descriptionView: DescriptionView

    private val exerciseType: ExerciseType
        get() = (requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType)

    private val exerciseName: ExerciseName = ExerciseName.ALPHABET_ADJECTIVES
        get() {
            return when (exerciseType) {
                is ExerciseType.Common -> field
                is ExerciseType.Training -> field
            }
        }

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
            .descriptionComponent()
            .create(exerciseName)
            .inject(this)
    }

    private fun initView() {
        descriptionView = DescriptionView(
            FragmentDescriptionBinding.bind(requireView()),
            object : DescriptionView.Callback {
                override fun onOpenExercise() {
                    host<Router>().onOpenExercise(exerciseType)
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }

                override fun onNextChart() {
                    viewModel.onNextChart()
                }

                override fun onPreviousChart() {
                    viewModel.onPreviousChart()
                }
            })
    }

    private fun initObservers() {
        viewModel.description.observe(viewLifecycleOwner, descriptionView::setDescription)
    }
}