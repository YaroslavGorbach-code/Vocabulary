package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.App
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAlphabetBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation.AlphabetViewModel
import javax.inject.Inject

class AlphabetFragment : Fragment(R.layout.fragment_exercise_alphabet),
    TimeEndDialog.Host,
    ExerciseFinishDialog.Host {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = AlphabetFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AlphabetViewModel> { viewModelFactory }

    private lateinit var alphabetView: AlphabetView

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
            .alphabetComponent()
            .create(exerciseType)
            .inject(this)
    }

    private fun initView() {
        alphabetView = AlphabetView(
            FragmentExerciseAlphabetBinding.bind(requireView()),
            object : AlphabetView.Callback {
                override fun onNewLetter() {
                    lifecycleScope.launch {
                        viewModel.onNextLetterClick()
                    }
                }

                override fun onTimeEnd() {
                    TimeEndDialog.newInstance(viewModel.passedLettersCount)
                        .show(childFragmentManager, null)
                }

                override fun onGameFinished() {
                    // TODO: 7/19/2021 calculate average time
                    ExerciseFinishDialog.newInstance(30).show(childFragmentManager, null)
                    viewModel.onTimerFinished()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })

        alphabetView.setDescriptionText(viewModel.description)
        alphabetView.setExerciseName(requireContext().getString((exerciseType.getExerciseName()).id))
    }

    private fun initObservers() {
        viewModel.letter.observe(viewLifecycleOwner, alphabetView::setLetter)
        viewModel.progress.observe(viewLifecycleOwner, alphabetView::setProgress)
    }

    override fun onDialogCancel() {
        requireActivity().onBackPressed()
    }
}