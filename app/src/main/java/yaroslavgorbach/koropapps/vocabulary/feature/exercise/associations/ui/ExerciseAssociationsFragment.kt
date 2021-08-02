package yaroslavgorbach.koropapps.vocabulary.feature.exercise.associations.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.associations.presentation.ExerciseAssociationsViewModel

class ExerciseAssociationsFragment : Fragment(R.layout.fragment_exercise) {

    private val viewModel by viewModels<ExerciseAssociationsViewModel>()

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
            })
        exerciseView.setDescriptionText(viewModel.descriptionText)
    }
}