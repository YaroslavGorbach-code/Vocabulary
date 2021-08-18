package yaroslavgorbach.koropapps.vocabulary.workflow

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui.DescriptionFragment
import yaroslavgorbach.koropapps.vocabulary.workflow.factory.CreateExerciseFragmentFactory

@FlowPreview
class ExerciseWorkflow : Fragment(R.layout.workflow_exercise), DescriptionFragment.Router {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = ExerciseWorkflow().apply {
            arguments = when (exerciseType) {
                is ExerciseType.Common -> bundleOf(ARG_EXERCISE_TYPE to exerciseType)
                is ExerciseType.Training -> bundleOf(ARG_EXERCISE_TYPE to exerciseType)
            }
        }
    }

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                when (exerciseType) {
                    is ExerciseType.Common -> {
                        add(R.id.exercise_container, DescriptionFragment.newInstance(exerciseType))
                    }
                    is ExerciseType.Training -> {
                        add(R.id.exercise_container, DescriptionFragment.newInstance(exerciseType))
                    }
                }
            }
        }
    }

    override fun onOpenExercise(exerciseType: ExerciseType) {
        val fragment: Fragment = when (exerciseType) {
            is ExerciseType.Common -> {
                CreateExerciseFragmentFactory().create(exerciseType.name, exerciseType)
            }
            is ExerciseType.Training -> {
                CreateExerciseFragmentFactory().create(exerciseType.name, exerciseType)
            }
        }
        childFragmentManager.commit {
            replace(R.id.exercise_container, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }
}