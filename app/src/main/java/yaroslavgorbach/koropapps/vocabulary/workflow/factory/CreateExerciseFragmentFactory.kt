package yaroslavgorbach.koropapps.vocabulary.workflow.factory

import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.ui.WordExerciseFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithcategory.ui.WordWithCategoryFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.ui.WordWithTimerExerciseFragment

class CreateExerciseFragmentFactory {
    fun create(exerciseName: ExerciseName, exerciseType: ExerciseType): Fragment {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.ALPHABET_NOUN -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.ALPHABET_VERBS -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.TAUTOGRAMS -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.NARRATOR_ADJECTIVES -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.NARRATOR_NOUN -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.NARRATOR_VERBS -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.ANTONYMS_AND_SYNONYMS -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.TEN -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.ASSOCIATIONS -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.REMEMBER_ALL -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.GAME_I_KNOW_5_NAMES -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.THREE_LITER_JAR -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.LIST_OF_CATEGORIES -> {
                WordWithCategoryFragment.newInstance(exerciseType)
            }
            ExerciseName.THREE_LETTERS -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.HALF -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.SPECIFICATIONS -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.DICTIONARY_ADJECTIVES -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.DICTIONARY_NOUN -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.DICTIONARY_VERBS -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.LINGUISTIC_PYRAMIDS -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.STORYTELLER_IMPROVISER -> TODO()
            ExerciseName.ADVANCED_BINDING -> TODO()
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT -> TODO()
            ExerciseName.OTHER_ABBREVIATIONS -> TODO()
            ExerciseName.MAGIC_NAMING -> TODO()
            ExerciseName.BUYING_SELLING -> TODO()
            ExerciseName.CO_AUTHORED_WITH_DAHL -> TODO()
            ExerciseName.RORSCHACH_TEST -> TODO()
            ExerciseName.WILL_NOT_BE_WORSE -> TODO()
            ExerciseName.QUESTION_ANSWER -> TODO()
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS -> TODO()
        }
    }
}