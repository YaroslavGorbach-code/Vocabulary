package yaroslavgorbach.koropapps.vocabulary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.settings.ChangeIsFirstAppOpenToFalseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveCurrentThemeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveIsFirstAppOpenInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveUiModeInteractor
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.ui.AboutAppPagerFragment
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
class MainActivity : AppCompatActivity(), NavigationFragment.Router, AboutAppPagerFragment.Router,
    TrainingFragment.Router, SettingsFragment.ThemeChangedListener {

    @Inject
    lateinit var observeCurrentThemeInteractor: ObserveCurrentThemeInteractor

    @Inject
    lateinit var observeUiModeInteractor: ObserveUiModeInteractor

    @Inject
    lateinit var observeIsFirsAppOpenInteractor: ObserveIsFirstAppOpenInteractor

    @Inject
    lateinit var changeIsFirsAppOpenToFalseInteractor: ChangeIsFirstAppOpenToFalseInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setCurrentTheme {
            if (savedInstanceState == null) {
                getIsFirstAppOpenAndNavigateToTheFirstScreen()
            }
        }

    }

    private fun getIsFirstAppOpenAndNavigateToTheFirstScreen() {
        lifecycleScope.launch {
            if (observeIsFirsAppOpenInteractor().first()) {
                changeIsFirsAppOpenToFalseInteractor()
                navigateToAboutAppScreen()
            } else {
                onNavigationScreen()
            }
        }
    }

    private fun setCurrentTheme(doAfterSet: () -> Unit) {
        lifecycleScope.launch {
            onThemeChanged(observeCurrentThemeInteractor().first(), false)
            onUiModeChanged(observeUiModeInteractor().first())
            setContentView(R.layout.activity_main)
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

    override fun onNavigationScreen() {
        val fragment = NavigationFragment.newInstance()
        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
            setPrimaryNavigationFragment(fragment)
        }
    }

    private fun navigateToAboutAppScreen() {
        val fragment = AboutAppPagerFragment.newInstance()
        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
        }
    }

    override fun onThemeChanged(theme: Theme, isNeedToRecreate: Boolean) {
        setTheme(theme.res.id)

        if (isNeedToRecreate){
           recreate()
        }
    }

    override fun onUiModeChanged(uiMode: UiMode) {
        when (uiMode) {
            is UiMode.Dark -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            is UiMode.Light -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
    }
}