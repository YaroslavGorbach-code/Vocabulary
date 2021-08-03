package yaroslavgorbach.koropapps.vocabulary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui.ExercisesListFragment
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

    override fun openDescription(exercise: Exercise) {
        val fragment = ExerciseWorkflow.newInstance(exercise.name)
        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            setPrimaryNavigationFragment(fragment)
            addToBackStack(null)
        }
    }
}