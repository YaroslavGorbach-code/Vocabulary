package yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAlphabetBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet.presentation.ExerciseAlphabetViewModel

class ExerciseAlphabetFragment : Fragment(R.layout.fragment_exercise_alphabet), TimeEndDialog.Host,
    ExerciseFinishDialog.Host {

    companion object {
        fun newInstance(exerciseName: ExerciseName): ExerciseAlphabetFragment {
            return ExerciseAlphabetFragment().apply {
                arguments = bundleOf(
                    "exerciseName" to exerciseName
                )
            }
        }
    }

    private lateinit var exerciseAlphabetView: ExerciseAlphabetView

    private val viewModel by viewModels<ExerciseAlphabetViewModel>()

    private val exName: ExerciseName
        get() = requireArguments()["exerciseName"] as ExerciseName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObservers()
    }

    private fun initView(view: View) {
        exerciseAlphabetView = ExerciseAlphabetView(
            FragmentExerciseAlphabetBinding.bind(view),
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

                override fun onGameEnd() {
                    // TODO: 7/19/2021 calculate average time
                    ExerciseFinishDialog.newInstance(30).show(childFragmentManager, null)
                    viewModel.stopTimer()
                }

                override fun onBack() {
                    childFragmentManager.popBackStack()
                }
            })
        exerciseAlphabetView.setExerciseName(requireContext().getString(exName.id))
    }

    private fun initObservers() {
        viewModel.letter.observe(viewLifecycleOwner, exerciseAlphabetView::setLetter)
        exerciseAlphabetView.setDescriptionText(viewModel.getDescriptionText(exName))
        viewModel.progress.observe(viewLifecycleOwner, exerciseAlphabetView::setProgress)
    }

    override fun onDialogCancel() {
        requireActivity().onBackPressed()
    }


}