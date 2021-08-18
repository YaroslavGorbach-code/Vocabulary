package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.ui

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
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.presentation.ExerciseJarViewModel

class ExerciseJarFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = ExerciseJarFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    private val viewModel by viewModels<ExerciseJarViewModel>()

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
                exerciseView.setDescriptionText(viewModel.descriptionText)
                exerciseView.setExerciseName((exerciseType as ExerciseType.Common).name)
            }
            is ExerciseType.Training -> {
                exerciseView.setDescriptionText(viewModel.descriptionText)
                exerciseView.setExerciseName((exerciseType as ExerciseType.Training).name)
            }
        }

    }

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)
    }

}