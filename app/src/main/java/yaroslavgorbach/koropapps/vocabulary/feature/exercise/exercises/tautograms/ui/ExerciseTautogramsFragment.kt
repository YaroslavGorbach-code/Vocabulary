package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.presentation.ExerciseTautogramsViewModel

class ExerciseTautogramsFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = ExerciseTautogramsFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    private val viewModel by viewModels<ExerciseTautogramsViewModel>()

    private lateinit var exerciseView: ExerciseView

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObservers()
    }

    private fun initView(view: View) {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(view),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.generateWord()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })

        when (exerciseType) {
            is ExerciseType.Common -> {
                exerciseView.setExerciseName((exerciseType as ExerciseType.Common).name)
                exerciseView.setDescriptionText(viewModel.descriptionText)
            }
            is ExerciseType.Training -> {
                exerciseView.setExerciseName((exerciseType as ExerciseType.Training).name)
                exerciseView.setDescriptionText(viewModel.descriptionText)
            }
        }
    }

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)
    }
}