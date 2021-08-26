package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.ui

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
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.presentation.AntonymsSynonymsViewModel
import javax.inject.Inject

class AntonymsSynonymsFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType): AntonymsSynonymsFragment {
            return AntonymsSynonymsFragment().apply {
                arguments = bundleOf(
                    ARG_EXERCISE_TYPE to exerciseType
                )
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AntonymsSynonymsViewModel by viewModels { viewModelFactory }

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
            .antonymsSynonymsComponent()
            .create(exerciseType)
            .inject(this)
    }

    private fun initView() {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(requireView()),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.onNextWordClick()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })

        exerciseView.setExerciseName(exerciseType.getExerciseName())

    }

    private fun initObservers() {
        viewModel.descriptionText.observe(viewLifecycleOwner, exerciseView::setDescriptionText)
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)
        viewModel.exercise.observe(viewLifecycleOwner) { exercise ->
            exerciseView.setAimAndPerformed(exercise.aim, exercise.performed)
            if (exercise.isFinished) {
                requireActivity().onBackPressed()
            }
        }
    }
}