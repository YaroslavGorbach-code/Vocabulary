package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation.DescriptionViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.host

@FlowPreview
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

    private val viewModel by viewModels<DescriptionViewModel>()

    private lateinit var descriptionView: DescriptionView

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
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
            })
    }

    private fun initObservers() {
        when (exerciseType) {
            is ExerciseType.Common -> {
                viewModel.getDescription((exerciseType as ExerciseType.Common).name)
                    .observe(viewLifecycleOwner, descriptionView::setDescription)
            }
            is ExerciseType.Training -> {
                viewModel.getDescription((exerciseType as ExerciseType.Training).name)
                    .observe(viewLifecycleOwner, descriptionView::setDescription)
            }
        }
    }
}