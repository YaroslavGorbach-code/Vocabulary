package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.WordCategory

class ExerciseNameToWordCategoryMapper {

    fun map(exerciseName: ExerciseName): WordCategory {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> WordCategory.LETTERS
            ExerciseName.ALPHABET_NOUN -> WordCategory.LETTERS
            ExerciseName.ALPHABET_VERBS -> WordCategory.LETTERS
            ExerciseName.TAUTOGRAMS -> WordCategory.LETTERS
            ExerciseName.NARRATOR_NOUN -> WordCategory.NONE
            ExerciseName.NARRATOR_ADJECTIVES -> WordCategory.NONE
            ExerciseName.NARRATOR_VERBS -> WordCategory.NONE
            ExerciseName.ANTONYMS_AND_SYNONYMS -> WordCategory.FILLINGS
            ExerciseName.TEN -> WordCategory.NOUNS
            ExerciseName.ASSOCIATIONS -> WordCategory.NOUNS
            ExerciseName.REMEMBER_ALL -> WordCategory.LETTERS
            ExerciseName.GAME_I_KNOW_5_NAMES -> WordCategory.CATEGORY
            ExerciseName.THREE_LITER_JAR -> WordCategory.LETTERS
            ExerciseName.LIST_OF_CATEGORIES -> WordCategory.LETTERS
            ExerciseName.THREE_LETTERS -> WordCategory.LETTERS
            ExerciseName.HALF -> WordCategory.LETTERS
            ExerciseName.SPECIFICATIONS -> WordCategory.NOUNS
            ExerciseName.DICTIONARY_ADJECTIVES -> WordCategory.NONE
            ExerciseName.DICTIONARY_NOUN -> WordCategory.NONE
            ExerciseName.DICTIONARY_VERBS -> WordCategory.NONE
            ExerciseName.LINGUISTIC_PYRAMIDS -> TODO()
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> TODO()
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