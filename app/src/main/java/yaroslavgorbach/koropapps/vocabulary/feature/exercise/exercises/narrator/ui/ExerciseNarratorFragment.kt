package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.presentation.ExerciseNarratorViewModel


class ExerciseNarratorFragment : Fragment(R.layout.fragment_exercise) {

    companion object {
        fun newInstance(exerciseName: ExerciseName): ExerciseNarratorFragment {
            return ExerciseNarratorFragment().apply {
                arguments = bundleOf("exerciseName" to exerciseName)
            }
        }
    }

    private lateinit var exerciseView: ExerciseView

    private val viewModel by viewModels<ExerciseNarratorViewModel>()

    private val exName: ExerciseName
        get() = requireArguments()["exerciseName"] as ExerciseName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(requireView()),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.generateNumberOfWords()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })
        exerciseView.setDescriptionText(viewModel.getDescriptionText(exName))
        exerciseView.setExerciseName(exName)
        exerciseView.setShortDescriptionText(
            requireContext().getString(R.string.number_of_words_in_story)
        )
    }

    private fun initObservers() {
        viewModel.numberOfWords.observe(viewLifecycleOwner, exerciseView::setWord)
    }

}