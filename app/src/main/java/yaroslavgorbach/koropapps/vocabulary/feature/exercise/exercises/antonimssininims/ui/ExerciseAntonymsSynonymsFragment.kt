package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonimssininims.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonimssininims.presentation.ExerciseAntonymsSynonymsViewModel

class ExerciseAntonymsSynonymsFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = ExerciseAntonymsSynonymsFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    private lateinit var exerciseView: ExerciseView

    private val viewModel by viewModels<ExerciseAntonymsSynonymsViewModel>()

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewWithExerciseType(exerciseType)
        initObserversWithExerciseType(exerciseType)
    }

    private fun initViewWithExerciseType(exerciseType: ExerciseType) {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(requireView()),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.generateDescriptionTextAndNewWord()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })

        when (exerciseType) {
            is ExerciseType.Common -> {
                exerciseView.setExerciseName(exerciseType.name)
            }
            is ExerciseType.Training -> {
                exerciseView.setExerciseName(exerciseType.name)
            }
        }
    }

    private fun initObserversWithExerciseType(exerciseType: ExerciseType) {
        viewModel.descriptionText.observe(viewLifecycleOwner, exerciseView::setDescriptionText)
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)

        when (exerciseType) {
            is ExerciseType.Common -> {
            }
            is ExerciseType.Training -> {
                viewModel.anim.observe(viewLifecycleOwner) {}
                viewModel.performed.observe(viewLifecycleOwner) {}
            }
        }

    }

}