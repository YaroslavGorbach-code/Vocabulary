package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseWithTimerBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.InfoDialog
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.common.ui.ExerciseTimeEndDialog
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.presentation.WordWithTimerViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.consume
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import javax.inject.Inject

class WordWithTimerExerciseFragment : Fragment(R.layout.fragment_exercise_with_timer),
    ExerciseTimeEndDialog.Host, InfoDialog.Host {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = WordWithTimerExerciseFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<WordWithTimerViewModel> { viewModelFactory }

    private lateinit var wordWithTimerExerciseView: WordWithTimerExerciseView

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
        appComponent()
            .wordWithTimerExerciseComponent()
            .create(exerciseType)
            .inject(this)
    }

    private fun initView() {
        wordWithTimerExerciseView = WordWithTimerExerciseView(
            FragmentExerciseWithTimerBinding.bind(requireView()),
            object : WordWithTimerExerciseView.Callback {
                override fun onClick() {
                    viewModel.onNextClick()
                }

                override fun onTimeEnd() {
                    showTimeEndDialog()
                    viewModel.onTimerFinished()
                }

                override fun onBack() {
                    onBackPressed()
                }
            })

        wordWithTimerExerciseView.setDescriptionText(viewModel.description)

        wordWithTimerExerciseView.setExerciseName(exerciseType.getExerciseName())
    }

    private fun showTimeEndDialog() {
        ExerciseTimeEndDialog.newInstance(viewModel.numberOnNextCLicked)
            .show(childFragmentManager, null)
    }

    private fun initObservers() {
        viewModel.timerState.observe(viewLifecycleOwner, wordWithTimerExerciseView::setTimerState)

        viewModel.letter.observe(viewLifecycleOwner, wordWithTimerExerciseView::setWord)

        viewModel.finisExerciseEvent.consume(viewLifecycleOwner) {
            showFinishExerciseDialog()
            viewModel.onTimerFinished()
        }
    }

    private fun showFinishExerciseDialog() {
        InfoDialog.newInstance(
            title = getString(R.string.finish_of_exercise),
            message = getString(R.string.average_time_on_word) + " ${viewModel.averageTimeOnWord}"
        ).show(childFragmentManager, null)
    }

    override fun onTimeEndDialogCancel() {
        onBackPressed()
    }

    override fun onInfoDialogCancel() {
        onBackPressed()
    }

}