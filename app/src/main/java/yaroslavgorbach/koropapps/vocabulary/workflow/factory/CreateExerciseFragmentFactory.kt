package yaroslavgorbach.koropapps.vocabulary.workflow.factory

import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui.ExerciseAlphabetFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.ui.AntonymsSynonymsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.ui.AssociationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.game.ui.GameFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.ui.ExerciseJarFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.ui.ExerciseNarratorFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.ui.ExerciseRememberAllFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.ui.ExerciseTautogramsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ten.ui.ExerciseTenFragment

class CreateExerciseFragmentFactory {
    fun create(exerciseName: ExerciseName, exerciseType: ExerciseType): Fragment {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> { ExerciseAlphabetFragment.newInstance(exerciseType) }
            ExerciseName.ALPHABET_NOUN -> { ExerciseAlphabetFragment.newInstance(exerciseType) }
            ExerciseName.ALPHABET_VERBS -> { ExerciseAlphabetFragment.newInstance(exerciseType) }
            ExerciseName.TAUTOGRAMS -> { ExerciseTautogramsFragment.newInstance(exerciseType) }
            ExerciseName.NARRATOR_ADJECTIVES -> { ExerciseNarratorFragment.newInstance(exerciseType) }
            ExerciseName.NARRATOR_NOUN -> { ExerciseNarratorFragment.newInstance(exerciseType) }
            ExerciseName.NARRATOR_VERBS -> { ExerciseNarratorFragment.newInstance(exerciseType) }
            ExerciseName.ANTONYMS_AND_SYNONYMS -> { AntonymsSynonymsFragment.newInstance(exerciseType) }
            ExerciseName.TEN -> { ExerciseTenFragment.newInstance(exerciseType) }
            ExerciseName.ASSOCIATIONS -> { AssociationsFragment.newInstance(exerciseType) }
            ExerciseName.REMEMBER_ALL -> { ExerciseRememberAllFragment.newInstance(exerciseType) }
            ExerciseName.GAME_I_KNOW_5_NAMES -> { GameFragment.newInstance(exerciseType) }
            ExerciseName.THREE_LITER_JAR -> { ExerciseJarFragment.newInstance(exerciseType) }
        }
    }
}