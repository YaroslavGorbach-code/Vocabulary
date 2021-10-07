package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class ExerciseNameToExerciseCategoryMapper {

    fun map(exerciseName: ExerciseName): ExerciseCategory{
        return when(exerciseName){
            ExerciseName.ALPHABET_ADJECTIVES -> ExerciseCategory.VOCABULARY
            ExerciseName.ALPHABET_NOUN -> ExerciseCategory.VOCABULARY
            ExerciseName.ALPHABET_VERBS -> ExerciseCategory.VOCABULARY
            ExerciseName.TAUTOGRAMS -> ExerciseCategory.VOCABULARY
            ExerciseName.NARRATOR_NOUN -> ExerciseCategory.VOCABULARY
            ExerciseName.NARRATOR_ADJECTIVES -> ExerciseCategory.VOCABULARY
            ExerciseName.NARRATOR_VERBS -> ExerciseCategory.VOCABULARY
            ExerciseName.ANTONYMS_AND_SYNONYMS -> ExerciseCategory.VOCABULARY
            ExerciseName.TEN -> ExerciseCategory.VOCABULARY
            ExerciseName.ASSOCIATIONS -> ExerciseCategory.VOCABULARY
            ExerciseName.REMEMBER_ALL -> ExerciseCategory.VOCABULARY
            ExerciseName.GAME_I_KNOW_5_NAMES -> ExerciseCategory.VOCABULARY
            ExerciseName.THREE_LITER_JAR -> ExerciseCategory.VOCABULARY
            ExerciseName.LIST_OF_CATEGORIES -> ExerciseCategory.VOCABULARY
            ExerciseName.THREE_LETTERS -> ExerciseCategory.VOCABULARY
            ExerciseName.HALF -> ExerciseCategory.VOCABULARY
            ExerciseName.SPECIFICATIONS -> ExerciseCategory.VOCABULARY
            ExerciseName.DICTIONARY_ADJECTIVES -> ExerciseCategory.VOCABULARY
            ExerciseName.DICTIONARY_NOUN -> ExerciseCategory.VOCABULARY
            ExerciseName.DICTIONARY_VERBS -> ExerciseCategory.VOCABULARY
            ExerciseName.LINGUISTIC_PYRAMIDS -> ExerciseCategory.COMMUNICATION
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> ExerciseCategory.COMMUNICATION
            ExerciseName.STORYTELLER_IMPROVISER -> ExerciseCategory.COMMUNICATION
            ExerciseName.ADVANCED_BINDING -> ExerciseCategory.COMMUNICATION
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT -> ExerciseCategory.COMMUNICATION
            ExerciseName.OTHER_ABBREVIATIONS -> ExerciseCategory.COMMUNICATION
            ExerciseName.MAGIC_NAMING -> ExerciseCategory.COMMUNICATION
            ExerciseName.BUYING_SELLING -> ExerciseCategory.COMMUNICATION
            ExerciseName.CO_AUTHORED_WITH_DAHL -> ExerciseCategory.COMMUNICATION
            ExerciseName.RORSCHACH_TEST -> ExerciseCategory.COMMUNICATION
            ExerciseName.WILL_NOT_BE_WORSE -> ExerciseCategory.COMMUNICATION
            ExerciseName.QUESTION_ANSWER -> ExerciseCategory.COMMUNICATION
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS -> ExerciseCategory.COMMUNICATION
        }
    }
}