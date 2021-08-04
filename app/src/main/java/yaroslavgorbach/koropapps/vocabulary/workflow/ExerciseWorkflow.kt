package yaroslavgorbach.koropapps.vocabulary.workflow

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.description.ui.DescriptionFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet.ui.ExerciseAlphabetFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.antonimssininims.ui.ExerciseAntonymsSynonymsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.associations.ui.ExerciseAssociationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.game.ui.ExerciseGameFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.jar.ui.ExerciseJarFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.narrator.ui.ExerciseNarratorFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.rememberall.ui.ExerciseRememberAllFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.tautograms.ui.ExerciseTautogramsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.ten.ui.ExerciseTenFragment

@FlowPreview
class ExerciseWorkflow : Fragment(R.layout.workflow_exercise), DescriptionFragment.Router {

    companion object {
        private const val ARG_EXERCISE_NAME = "ARG_EXERCISE_NAME"
        fun newInstance(exerciseName: ExerciseName) = ExerciseWorkflow().apply {
            arguments = bundleOf(
                ARG_EXERCISE_NAME to exerciseName
            )
        }
    }

    private val exName: ExerciseName
        get() = requireArguments()[ARG_EXERCISE_NAME] as ExerciseName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                add(R.id.exercise_container, DescriptionFragment.newInstance(exName))
            }
        }
    }

    override fun onOpenExercise(exerciseName: ExerciseName) {
        val fragment: Fragment
        when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> {
                fragment = ExerciseAlphabetFragment.newInstance(exerciseName)
            }
            ExerciseName.ALPHABET_NOUN -> {
                fragment = ExerciseAlphabetFragment.newInstance(exerciseName)
            }
            ExerciseName.ALPHABET_VERBS -> {
                fragment = ExerciseAlphabetFragment.newInstance(exerciseName)
            }
            ExerciseName.TAUTOGRAMS -> {
                fragment = ExerciseTautogramsFragment()
            }
            ExerciseName.NARRATOR_ADJECTIVES -> {
                fragment = ExerciseNarratorFragment.newInstance(exerciseName)
            }
            ExerciseName.NARRATOR_NOUN -> {
                fragment = ExerciseNarratorFragment.newInstance(exerciseName)
            }
            ExerciseName.NARRATOR_VERBS -> {
                fragment = ExerciseNarratorFragment.newInstance(exerciseName)
            }
            ExerciseName.ANTONYMS_AND_SYNONYMS -> {
                fragment = ExerciseAntonymsSynonymsFragment()
            }
            ExerciseName.TEN -> {
                fragment = ExerciseTenFragment()
            }
            ExerciseName.ASSOCIATIONS -> {
                fragment = ExerciseAssociationsFragment()
            }
            ExerciseName.REMEMBER_ALL -> {
                fragment = ExerciseRememberAllFragment()
            }
            ExerciseName.GAME_I_KNOW_5_NAMES -> {
                fragment = ExerciseGameFragment()
            }
            ExerciseName.THREE_LITER_JAR -> {
                fragment = ExerciseJarFragment()
            }
        }
        childFragmentManager.commit {
            replace(R.id.exercise_container, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }
}