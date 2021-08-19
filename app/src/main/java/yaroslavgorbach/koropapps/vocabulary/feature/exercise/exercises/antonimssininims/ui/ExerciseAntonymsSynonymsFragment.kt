package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonimssininims.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonimssininims.presentation.ExerciseAntonymsSynonymsViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonimssininims.presentation.ExerciseAntonymsSynonymsViewModelFactory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType

class ExerciseAntonymsSynonymsFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = ExerciseAntonymsSynonymsFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType

    private lateinit var exerciseView: ExerciseView

    private val viewModel: ExerciseAntonymsSynonymsViewModel by viewModels {
        ExerciseAntonymsSynonymsViewModelFactory(exerciseType, requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(requireView()),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.refreshData()
                    viewModel.incrementExercisePerformed()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })

        when (exerciseType) {
            is ExerciseType.Common -> {
                exerciseView.setExerciseName((exerciseType as ExerciseType.Common).name)
            }
            is ExerciseType.Training -> {
                exerciseView.setExerciseName((exerciseType as ExerciseType.Training).name)
            }
        }
    }

    private fun initObservers() {
        viewModel.descriptionText.observe(viewLifecycleOwner, exerciseView::setDescriptionText)
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)
        viewModel.exercise.observe(viewLifecycleOwner) { exercise ->
            exerciseView.setAimAndPerformed(exercise.aim, exercise.performed)
            if (exercise.isFinished) {
                requireActivity().onBackPressed()
            }
        }
    }
}