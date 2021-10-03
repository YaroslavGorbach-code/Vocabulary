package yaroslavgorbach.koropapps.vocabulary.workflow.factory

import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.communication.abbreviations.ui.AbbreviationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.alphabet.ui.AlphabetFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.antonymssynonyms.ui.AntonymsSynonymsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.associations.ui.AssociationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.dictionary.ui.DictionaryFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.game.ui.GameFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.letters.ui.LettersExerciseFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.listofcategories.ui.ListOfCategoriesFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.narrator.ui.NarratorFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.specifications.ui.SpecificationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.ten.ui.TenFragment

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
                LettersExerciseFragment.newInstance(exerciseType)
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
                LettersExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.GAME_I_KNOW_5_NAMES -> {
                GameFragment.newInstance(exerciseType)
            }
            ExerciseName.THREE_LITER_JAR -> {
                LettersExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.LIST_OF_CATEGORIES -> {
                ListOfCategoriesFragment.newInstance(exerciseType)
            }
            ExerciseName.THREE_LETTERS -> {
                LettersExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.HALF -> {
                LettersExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.SPECIFICATIONS -> {
                SpecificationsFragment.newInstance(exerciseType)
            }
            ExerciseName.DICTIONARY_ADJECTIVES -> {
                DictionaryFragment.newInstance(exerciseType)
            }
            ExerciseName.DICTIONARY_NOUN -> {
                DictionaryFragment.newInstance(exerciseType)
            }
            ExerciseName.DICTIONARY_VERBS -> {
                DictionaryFragment.newInstance(exerciseType)
            }
            ExerciseName.LINGUISTIC_PYRAMIDS -> TODO()
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> TODO()
            ExerciseName.STORYTELLER_IMPROVISER -> TODO()
            ExerciseName.ADVANCED_BINDING -> TODO()
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT -> TODO()
            ExerciseName.OTHER_ABBREVIATIONS -> {
                AbbreviationsFragment.newInstance(exerciseType)
            }
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