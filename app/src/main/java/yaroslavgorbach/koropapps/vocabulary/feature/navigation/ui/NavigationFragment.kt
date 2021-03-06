package yaroslavgorbach.koropapps.vocabulary.feature.navigation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentNavigationBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.ExercisesListFragment
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.ui.ProfileFragment
import yaroslavgorbach.koropapps.vocabulary.feature.records.ui.RecordsListFragment
import yaroslavgorbach.koropapps.vocabulary.utils.host

@InternalCoroutinesApi
class NavigationFragment : Fragment(R.layout.fragment_navigation), ExercisesListFragment.Router,
    ProfileFragment.Router {

    interface Router {
        fun onOpenDescription(exercise: ExerciseUi)
        fun onOpenTraining()
        fun onOpenLevel()
        fun onOpenSettings()
    }

    companion object {
        fun newInstance() = NavigationFragment()
    }

    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = ExercisesListFragment.newInstance()
            childFragmentManager.commit {
                replace(R.id.nav_container, fragment)
                setPrimaryNavigationFragment(fragment)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        navigationView = NavigationView(
            FragmentNavigationBinding.bind(requireView()),
            object : NavigationView.Callback {
                override fun onNavigateToExercisesList() {
                    val fragment = ExercisesListFragment.newInstance()
                    childFragmentManager.commit {
                        replace(R.id.nav_container, fragment)
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    }
                }

                override fun onNavigateToProfile() {
                    val fragment = ProfileFragment.newInstance()
                    childFragmentManager.commit {
                        replace(R.id.nav_container, fragment)
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    }
                }

                override fun onNavigateToRecords() {
                    val fragment = RecordsListFragment.newInstance()
                    childFragmentManager.commit {
                        replace(R.id.nav_container, fragment)
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    }
                }
            })
    }

    override fun openDescription(exercise: ExerciseUi) {
        host<Router>().onOpenDescription(exercise)
    }

    override fun openTraining() {
        host<Router>().onOpenTraining()
    }

    override fun onOpenLevelClick() {
        host<Router>().onOpenLevel()
    }

    override fun onOpenSettingsClick() {
        host<Router>().onOpenSettings()
    }

}