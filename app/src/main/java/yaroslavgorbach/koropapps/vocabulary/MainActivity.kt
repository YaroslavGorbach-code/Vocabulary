package yaroslavgorbach.koropapps.vocabulary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.ExercisesListFragment
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.TrainingFragment
import yaroslavgorbach.koropapps.vocabulary.workflow.ExerciseWorkflow

@InternalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity(R.layout.activity_main), ExercisesListFragment.Router {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = ExercisesListFragment()
            supportFragmentManager.commit {
                add(R.id.main_container, fragment)
                setPrimaryNavigationFragment(fragment)
            }
        }
    }

    override fun openDescription(exercise: ExerciseUi) {
        val fragment = ExerciseWorkflow.newInstance(exercise.name)
        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            setPrimaryNavigationFragment(fragment)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun openTraining() {
        val fragment = TrainingFragment.newInstance()
        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }
}