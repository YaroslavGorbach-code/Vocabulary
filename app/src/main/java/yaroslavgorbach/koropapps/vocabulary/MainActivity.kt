package yaroslavgorbach.koropapps.vocabulary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveCurrentThemeInteractor
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.navigation.ui.NavigationFragment
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui.LevelFragment
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui.SettingsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.TrainingFragment
import yaroslavgorbach.koropapps.vocabulary.workflow.ExerciseWorkflow
import javax.inject.Inject

@InternalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity(), NavigationFragment.Router,
    TrainingFragment.Router, SettingsFragment.ThemeChangedFragment {

    @Inject
    lateinit var observeCurrentThemeInteractor: ObserveCurrentThemeInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setCurrentTheme {
            setContentView(R.layout.activity_main)

            if (savedInstanceState == null) {
                val fragment = NavigationFragment.newInstance()

                supportFragmentManager.commit {
                    add(R.id.main_container, fragment)
                    setPrimaryNavigationFragment(fragment)
                }
            }
        }
    }

    private fun setCurrentTheme(doAfterSet: () -> Unit) {
        lifecycleScope.launch {
            onThemeChanged(observeCurrentThemeInteractor(this@MainActivity).first())
            doAfterSet()
        }
    }


    private fun initDagger() {
        (application as App).appComponent.inject(this)
    }

    override fun onOpenDescription(exercise: ExerciseUi) {
        val fragment = ExerciseWorkflow.newInstance(ExerciseType.Common(exercise.name))

        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            setPrimaryNavigationFragment(fragment)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun onOpenTraining() {
        val fragment = TrainingFragment.newInstance()

        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun onOpenLevel() {
        val fragment = LevelFragment.newInstance()

        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun onOpenSettings() {
        val fragment = SettingsFragment.newInstance()

        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun openDescription(exercise: TrainingExerciseUi) {
        val fragment = ExerciseWorkflow.newInstance(
            ExerciseType.Training(exercise.name, exercise.id)
        )

        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            setPrimaryNavigationFragment(fragment)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun onThemeChanged(theme: Theme) {
        setTheme(theme.res.id)
    }
}