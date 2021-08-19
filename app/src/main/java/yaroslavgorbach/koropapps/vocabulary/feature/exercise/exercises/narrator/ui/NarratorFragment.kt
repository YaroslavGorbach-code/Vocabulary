package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.App
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.presentation.NarratorViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import javax.inject.Inject


class NarratorFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = NarratorFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NarratorViewModel> { viewModelFactory }

    private lateinit var exerciseView: ExerciseView

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType

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
            .narratorComponent()
            .create(exerciseType)
            .inject(this)
    }

    private fun initView() {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(requireView()),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.generateNumberOfWords()
                    viewModel.incrementExercisePerformed()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })

        exerciseView.setShortDescriptionText(
            requireContext().getString(R.string.number_of_words_in_story)
        )

        // TODO: 8/18/2021 move description out of viewModel to exerciseType
        when (exerciseType) {
            is ExerciseType.Common -> {
                exerciseView.setDescriptionText(
                    viewModel.getDescriptionText((exerciseType as ExerciseType.Common).name)
                )
                exerciseView.setExerciseName((exerciseType as ExerciseType.Common).name)
            }
            is ExerciseType.Training -> {
                exerciseView.setDescriptionText(
                    viewModel.getDescriptionText((exerciseType as ExerciseType.Training).name)
                )
                exerciseView.setExerciseName((exerciseType as ExerciseType.Training).name)
            }
        }
    }

    private fun initObservers() {
        viewModel.numberOfWords.observe(viewLifecycleOwner, exerciseView::setWord)
        viewModel.exercise.observe(viewLifecycleOwner) { exercise ->
            if (exercise.isFinished) {
                requireActivity().onBackPressed()
            }
            exerciseView.setAimAndPerformed(exercise.aim, exercise.performed)
        }
    }
}