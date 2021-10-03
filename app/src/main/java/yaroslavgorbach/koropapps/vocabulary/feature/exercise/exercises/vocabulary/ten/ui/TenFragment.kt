package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.ten.ui

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
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.common.ui.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.ten.presentation.TenViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import javax.inject.Inject

class TenFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"

        fun newInstance(exerciseType: ExerciseType) = TenFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TenViewModel> { viewModelFactory }

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
            .tenComponent()
            .create(exerciseType)
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
            })

        exerciseView.setExerciseName(exerciseType.getExerciseName())
        exerciseView.setDescriptionText(viewModel.description)
    }

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)
        viewModel.exercise.observe(viewLifecycleOwner, exerciseView::setExercise)
    }
}