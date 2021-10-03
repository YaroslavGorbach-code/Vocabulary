package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.WordCategory

class ExerciseNameToWordCategoryMapper {

    fun map(exerciseName: ExerciseName): WordCategory {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> TODO()
            ExerciseName.ALPHABET_NOUN -> TODO()
            ExerciseName.ALPHABET_VERBS -> TODO()
            ExerciseName.TAUTOGRAMS -> WordCategory.LETTERS
            ExerciseName.NARRATOR_NOUN -> TODO()
            ExerciseName.NARRATOR_ADJECTIVES -> TODO()
            ExerciseName.NARRATOR_VERBS -> TODO()
            ExerciseName.ANTONYMS_AND_SYNONYMS -> TODO()
            ExerciseName.TEN -> TODO()
            ExerciseName.ASSOCIATIONS -> TODO()
            ExerciseName.REMEMBER_ALL -> WordCategory.LETTERS
            ExerciseName.GAME_I_KNOW_5_NAMES -> TODO()
            ExerciseName.THREE_LITER_JAR -> WordCategory.LETTERS
            ExerciseName.LIST_OF_CATEGORIES -> TODO()
            ExerciseName.THREE_LETTERS -> WordCategory.LETTERS
            ExerciseName.HALF -> WordCategory.LETTERS
            ExerciseName.SPECIFICATIONS -> TODO()
            ExerciseName.DICTIONARY_ADJECTIVES -> TODO()
            ExerciseName.DICTIONARY_NOUN -> TODO()
            ExerciseName.DICTIONARY_VERBS -> TODO()
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