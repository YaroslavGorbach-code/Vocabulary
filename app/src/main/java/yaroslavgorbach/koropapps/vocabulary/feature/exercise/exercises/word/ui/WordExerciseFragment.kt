package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.PermissionDeniedDialog
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.common.ui.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.presentation.WordExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.consume
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import javax.inject.Inject

class WordExerciseFragment : Fragment(R.layout.fragment_exercise), PermissionDeniedDialog.Host {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"

        fun newInstance(exerciseType: ExerciseType) = WordExerciseFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<WordExerciseViewModel> { viewModelFactory }

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
        appComponent()
            .wordExerciseComponent()
            .create(exerciseType, requireActivity().activityResultRegistry)
            .inject(this)
    }

    private fun initView() {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(requireView()),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.onNextClick()
                }

                override fun onBack() {
                    onBackPressed()
                }

                override fun onStartStopRecording() {
                    viewModel.onStartStopRecording()
                }
            })

        exerciseView.setExerciseName(exerciseType.getExerciseName())
    }

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner) {
            exerciseView.setWord(it)
            exerciseView.setDescriptionText(viewModel.description)
        }

        viewModel.exercise.observe(viewLifecycleOwner, exerciseView::setExercise)

        viewModel.showPermissionDeniedDialogEvent.consume(viewLifecycleOwner) {
            PermissionDeniedDialog.newInstance(getString(R.string.audio_permission_denied_message))
                .show(childFragmentManager, null)
        }

        viewModel.isVoiceRecorderRecording.observe(viewLifecycleOwner, exerciseView::setIsRecording)

        viewModel.isRecordSavedEvent.consume(viewLifecycleOwner){ exerciseView.showRecordSavedSnack() }
    }

    override fun onGrantPermissionClicked() {
        viewModel.onStartStopRecording()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStopRecord()
    }
}