package yaroslavgorbach.koropapps.vocabulary.feature.exercise.ten.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.ten.presentation.ExerciseTenVm

class ExerciseTenFragment : Fragment(R.layout.fragment_exercise) {

    private val viewModel by viewModels<ExerciseTenVm>()

    private lateinit var exerciseView: ExerciseView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObservers()
    }

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)
    }

    private fun initView(view: View) {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(view),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.generateWord()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })
        exerciseView.setDescriptionText(viewModel.descriptionText)
        exerciseView.setExerciseName(ExerciseName.TEN)
    }
}