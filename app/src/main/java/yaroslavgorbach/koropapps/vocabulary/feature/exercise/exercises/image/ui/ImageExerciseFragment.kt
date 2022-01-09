package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentImageExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.PermissionDeniedDialog
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.presentation.ImageExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.consume
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import javax.inject.Inject

class ImageExerciseFragment : Fragment(R.layout.fragment_image_exercise),
    PermissionDeniedDialog.Host {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"

        fun newInstance(exerciseType: ExerciseType) = ImageExerciseFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ImageExerciseViewModel> { viewModelFactory }

    private lateinit var exerciseView: ImageExerciseView

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
            .imageExerciseComponent()
            .create(exerciseType, requireActivity().activityResultRegistry)
            .inject(this)
    }

    private fun initView() {
        exerciseView = ImageExerciseView(
            FragmentImageExerciseBinding.bind(requireView()),
            object : ImageExerciseView.Callback {
                override fun onNext() {
                    viewModel.onNextClick()
                }

                override fun onBack() {
                    onBackPressed()
                }

                override fun onStartStopRecording() {
                    viewModel.onStartStopRecording(getString(exerciseType.getExerciseName().id))
                }
            })

        exerciseView.setExerciseName(exerciseType.getExerciseName())

    }

    private fun initObservers() {
        viewModel.image.observe(viewLifecycleOwner) {
            exerciseView.setImageUrl(it)
            exerciseView.setDescriptionText(viewModel.description)
        }

        viewModel.exercise.observe(viewLifecycleOwner, exerciseView::setExercise)

        viewModel.showPermissionDeniedDialogEvent.consume(viewLifecycleOwner) {
            PermissionDeniedDialog.newInstance(getString(R.string.audio_permission_denied_message))
                .show(childFragmentManager, null)
        }

        viewModel.isVoiceRecorderRecording.observe(viewLifecycleOwner, exerciseView::setIsRecording)

        viewModel.isRecordSavedEvent.consume(viewLifecycleOwner) { exerciseView.showRecordSavedSnack() }

        viewModel.isAutoRecordStart.observe(viewLifecycleOwner) { isAllow ->

            Log.i("dasdf", "isallow " + isAllow)
            if (isAllow) {
                viewModel.onStartStopRecording(getString(exerciseType.getExerciseName().id))
            }
        }
    }

    override fun onGrantPermissionClicked() {
        viewModel.onStartStopRecording(getString(exerciseType.getExerciseName().id))
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStopRecord()
    }

    override fun onDestroy() {
        viewModel.showInterstitial(requireActivity())
        super.onDestroy()
    }
}