package yaroslavgorbach.koropapps.vocabulary.feature.exercise.antonimssininims.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.ExerciseView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.antonimssininims.presentation.ExerciseAntonymsSynonymsViewModel

class ExerciseAntonymsSynonymsFragment : Fragment(R.layout.fragment_exercise) {

    private lateinit var exerciseView: ExerciseView

    private val viewModel by viewModels<ExerciseAntonymsSynonymsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObservers()
    }

    private fun initView(view: View) {
        exerciseView = ExerciseView(
            FragmentExerciseBinding.bind(view),
            object : ExerciseView.Callback {
                override fun onNext() {
                    viewModel.generateDescriptionTextAndNewWord()
                }

                override fun onBack() {
                    requireActivity().onBackPressed()
                }
            })
        exerciseView.setExerciseName(ExerciseName.ANTONYMS_AND_SYNONYMS)
    }

    private fun initObservers() {
        viewModel.descriptionText.observe(viewLifecycleOwner, exerciseView::setDescriptionText)
        viewModel.word.observe(viewLifecycleOwner, exerciseView::setWord)
    }
}