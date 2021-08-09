package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.presentation.ExerciseRememberAllViewModel

class ExerciseRememberAllFragment : Fragment(R.layout.fragment_exercise) {

    private val viewModel by viewModels<ExerciseRememberAllViewModel>()

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
        exerciseView.setExerciseName(ExerciseName.REMEMBER_ALL)
        exerciseView.setDescriptionText(viewModel.descriptionText)
    }
}