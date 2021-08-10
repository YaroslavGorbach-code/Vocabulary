package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.ExerciseTrainingUi
import yaroslavgorbach.koropapps.vocabulary.utils.host

class TrainingFragment : Fragment(R.layout.fragment_training) {

    interface Router {
        fun onOpenExercise(exercise: ExerciseTrainingUi)
    }

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private lateinit var trainingView: TrainingView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        trainingView = TrainingView(
            FragmentTrainingBinding.bind(view),
            object : TrainingView.Callback {
                override fun onExercise(exercise: ExerciseTrainingUi) {
                    host<Router>().onOpenExercise(exercise)
                }
            })
    }
}