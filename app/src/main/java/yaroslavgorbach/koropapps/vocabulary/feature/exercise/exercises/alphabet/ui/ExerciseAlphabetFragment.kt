package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAlphabetBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation.ExerciseAlphabetViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType

class ExerciseAlphabetFragment : Fragment(R.layout.fragment_exercise_alphabet), TimeEndDialog.Host,
    ExerciseFinishDialog.Host {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = ExerciseAlphabetFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    private lateinit var exerciseAlphabetView: ExerciseAlphabetView

    private val viewModel by viewModels<ExerciseAlphabetViewModel>()

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewWithExerciseType(exerciseType)
        initObserversWithExerciseType(exerciseType)
    }


    private fun initViewWithExerciseType(exerciseType: ExerciseType) {
        exerciseAlphabetView = ExerciseAlphabetView(
            FragmentExerciseAlphabetBinding.bind(requireView()),
            object : ExerciseAlphabetView.Callback {
                override fun onNewLetter() {
                    lifecycleScope.launch {
                        viewModel.refreshLetter()
                    }
                }

                override fun onTimeEnd() {
                    TimeEndDialog.newInstance(viewModel.lettersCount)
                        .show(childFragmentManager, null)
                }

                override fun onGameFinished() {
                    // TODO: 7/19/2021 calculate average time
                    ExerciseFinishDialog.newInstance(30).show(childFragmentManager, null)
                    viewModel.stopTimer()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })

        when (exerciseType) {
            is ExerciseType.Common -> {
                exerciseAlphabetView.setExerciseName(
                    requireContext().getString(exerciseType.name.id)
                )
                exerciseAlphabetView.setDescriptionText(
                    viewModel.getDescriptionText(exerciseType.name)
                )
            }
            is ExerciseType.Training -> {
                exerciseAlphabetView.setExerciseName(
                    requireContext().getString(exerciseType.name.id)
                )
                exerciseAlphabetView.setDescriptionText(
                    viewModel.getDescriptionText(exerciseType.name)
                )
            }
        }
    }

    private fun initObserversWithExerciseType(exerciseType: ExerciseType) {
        viewModel.letter.observe(viewLifecycleOwner, exerciseAlphabetView::setLetter)
        viewModel.progress.observe(viewLifecycleOwner, exerciseAlphabetView::setProgress)

        when (exerciseType) {
            is ExerciseType.Common -> {
            }
            is ExerciseType.Training -> {
            }
        }
    }

    override fun onDialogCancel() {
        requireActivity().onBackPressed()
    }
}