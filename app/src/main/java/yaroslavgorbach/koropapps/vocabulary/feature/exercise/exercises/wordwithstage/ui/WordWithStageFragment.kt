package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseWithStagesBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.presentation.WordWithStageViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import javax.inject.Inject

class WordWithStageFragment : Fragment(R.layout.fragment_exercise_with_stages) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"

        fun newInstance(exerciseType: ExerciseType) = WordWithStageFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<WordWithStageViewModel> { viewModelFactory }

    private lateinit var wordWithStageView: WordWithStageExerciseView

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
            .wordWithStageExerciseComponent()
            .create(exerciseType)
            .inject(this)
    }

    private fun initView() {
        wordWithStageView = WordWithStageExerciseView(
            FragmentExerciseWithStagesBinding.bind(requireView()),
            object : WordWithStageExerciseView.Callback {

                override fun onNextStage() {
                    viewModel.onNextStageClick()
                }

                override fun onBack() {
                    onBackPressed()
                }
            })

        wordWithStageView.setExerciseName(exerciseType.getExerciseName())
    }

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner, wordWithStageView::setWord)

        viewModel.stages.observe(viewLifecycleOwner, wordWithStageView::setStages)

        viewModel.exercise.observe(viewLifecycleOwner, wordWithStageView::setExercise)
    }
}