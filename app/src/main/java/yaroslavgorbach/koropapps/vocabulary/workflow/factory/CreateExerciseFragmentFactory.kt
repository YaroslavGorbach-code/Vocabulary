package yaroslavgorbach.koropapps.vocabulary.workflow.factory

import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui.AlphabetFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.ui.AntonymsSynonymsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.ui.AssociationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.game.ui.GameFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.half.ui.HalfFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.ui.JarFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.listofcategories.ui.ListOfCategoriesFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.ui.NarratorFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.ui.RememberAllFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.ui.TautogramsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ten.ui.TenFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.threeletters.ui.ThreeLettersFragment

class CreateExerciseFragmentFactory {
    fun create(exerciseName: ExerciseName, exerciseType: ExerciseType): Fragment {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> {
                AlphabetFragment.newInstance(exerciseType)
            }
            ExerciseName.ALPHABET_NOUN -> {
                AlphabetFragment.newInstance(exerciseType)
            }
            ExerciseName.ALPHABET_VERBS -> {
                AlphabetFragment.newInstance(exerciseType)
            }
            ExerciseName.TAUTOGRAMS -> {
                TautogramsFragment.newInstance(exerciseType)
            }
            ExerciseName.NARRATOR_ADJECTIVES -> {
                NarratorFragment.newInstance(exerciseType)
            }
            ExerciseName.NARRATOR_NOUN -> {
                NarratorFragment.newInstance(exerciseType)
            }
            ExerciseName.NARRATOR_VERBS -> {
                NarratorFragment.newInstance(exerciseType)
            }
            ExerciseName.ANTONYMS_AND_SYNONYMS -> {
                AntonymsSynonymsFragment.newInstance(exerciseType)
            }
            ExerciseName.TEN -> {
                TenFragment.newInstance(exerciseType)
            }
            ExerciseName.ASSOCIATIONS -> {
                AssociationsFragment.newInstance(exerciseType)
            }
            ExerciseName.REMEMBER_ALL -> {
                RememberAllFragment.newInstance(exerciseType)
            }
            ExerciseName.GAME_I_KNOW_5_NAMES -> {
                GameFragment.newInstance(exerciseType)
            }
            ExerciseName.THREE_LITER_JAR -> {
                JarFragment.newInstance(exerciseType)
            }
            ExerciseName.LIST_OF_CATEGORIES -> {
                ListOfCategoriesFragment.newInstance(exerciseType)
            }
            ExerciseName.THREE_LETTERS -> {
                ThreeLettersFragment.newInstance(exerciseType)
            }
            ExerciseName.HALF -> {
                HalfFragment.newInstance(exerciseType)
            }
        }
    }
}