package yaroslavgorbach.koropapps.vocabulary.feature.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.ExercisesListFragment
import yaroslavgorbach.koropapps.vocabulary.utils.host

@InternalCoroutinesApi
class NavigationFragment : Fragment(R.layout.fragment_navigation), ExercisesListFragment.Router {

    interface Router {
        fun openDescription(exercise: ExerciseUi)
        fun openTraining()
    }

    companion object {
        fun newInstance() = NavigationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = ExercisesListFragment.newInstance()
            childFragmentManager.commit {
                replace(R.id.nav_container, fragment)
                setPrimaryNavigationFragment(fragment)
            }
        }
    }

    override fun openDescription(exercise: ExerciseUi) {
        host<Router>().openDescription(exercise)
    }

    override fun openTraining() {
        host<Router>().openTraining()
    }

}