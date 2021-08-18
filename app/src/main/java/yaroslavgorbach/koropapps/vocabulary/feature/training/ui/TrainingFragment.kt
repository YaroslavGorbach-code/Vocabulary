package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.presentation.TrainingViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.host

@FlowPreview
class TrainingFragment : Fragment(R.layout.fragment_training) {

    interface Router {
        fun openDescription(exercise: TrainingExerciseUi)
    }

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private val viewModel by viewModels<TrainingViewModel>()

    private lateinit var trainingView: TrainingView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObservers()
    }

    private fun initView(view: View) {
        trainingView = TrainingView(
            FragmentTrainingBinding.bind(view),
            object : TrainingView.Callback {
                override fun onExercise(withExercises: TrainingExerciseUi) {
                    host<Router>().openDescription(withExercises)
                }
            })
    }

    private fun initObservers() {
        viewModel.trainingWithExercises.observe(
            viewLifecycleOwner,
            trainingView::setTrainingWitExercises
        )
    }

}