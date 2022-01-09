package yaroslavgorbach.koropapps.vocabulary.workflow.factory

import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.ui.ImageExerciseFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.ui.WordExerciseFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithcategory.ui.WordWithCategoryFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui.WordWithStageFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.ui.WordWithTimerExerciseFragment

class CreateExerciseFragmentFactory {
    fun create(exerciseName: ExerciseName, exerciseType: ExerciseType): Fragment {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES,
            ExerciseName.ALPHABET_NOUN,
            ExerciseName.ALPHABET_VERBS,
            ExerciseName.DICTIONARY_ADJECTIVES,
            ExerciseName.DICTIONARY_NOUN,
            ExerciseName.DICTIONARY_VERBS -> {
                WordWithTimerExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.LIST_OF_CATEGORIES -> {
                WordWithCategoryFragment.newInstance(exerciseType)

            }
            ExerciseName.GAME_I_KNOW_5_NAMES,
            ExerciseName.REMEMBER_ALL,
            ExerciseName.ASSOCIATIONS,
            ExerciseName.TEN,
            ExerciseName.ANTONYMS_AND_SYNONYMS,
            ExerciseName.NARRATOR_VERBS,
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.THREE_LITER_JAR,
            ExerciseName.THREE_LETTERS,
            ExerciseName.HALF,
            ExerciseName.SPECIFICATIONS,
            ExerciseName.LINGUISTIC_PYRAMIDS,
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE,
            ExerciseName.STORYTELLER_IMPROVISER,
            ExerciseName.ADVANCED_BINDING,
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
            ExerciseName.OTHER_ABBREVIATIONS,
            ExerciseName.MAGIC_NAMING,
            ExerciseName.BUYING_SELLING,
            ExerciseName.CO_AUTHORED_WITH_DAHL,
            ExerciseName.RORSCHACH_TEST,
            ExerciseName.WILL_NOT_BE_WORSE,
            ExerciseName.QUESTION_ANSWER,
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
            ExerciseName.COUP_OF_CONSCIOUSNESS,
            ExerciseName.PROBLEM_SOLVING,
            ExerciseName.TAUTOGRAMS -> {
                WordExerciseFragment.newInstance(exerciseType)
            }
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.SOUND_COMBINATIONS,
            ExerciseName.DIFFICULT_WORDS,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> {
                WordWithStageFragment.newInstance(exerciseType)
            }
            ExerciseName.COUP -> ImageExerciseFragment.newInstance(exerciseType)
        }
    }
}