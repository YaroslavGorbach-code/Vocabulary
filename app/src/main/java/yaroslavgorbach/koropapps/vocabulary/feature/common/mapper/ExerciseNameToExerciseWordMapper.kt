package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import android.content.res.Resources
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.WordCategory

object ExerciseNameToExerciseWordMapper {

    fun map(exerciseName: ExerciseName, resources: Resources): String {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> {
                resources.getStringArray(WordCategory.Letters().resArray).toList().random()
            }
            ExerciseName.ALPHABET_NOUN -> WordCategory.Letters()
            ExerciseName.ALPHABET_VERBS -> WordCategory.Letters()
            ExerciseName.TAUTOGRAMS -> WordCategory.Letters()
            ExerciseName.NARRATOR_NOUN -> WordCategory.None()
            ExerciseName.NARRATOR_ADJECTIVES -> WordCategory.None()
            ExerciseName.NARRATOR_VERBS -> WordCategory.None()
            ExerciseName.ANTONYMS_AND_SYNONYMS -> WordCategory.Fillings()
            ExerciseName.TEN -> WordCategory.NounsNotAlive()
            ExerciseName.ASSOCIATIONS -> WordCategory.NounsNotAlive()
            ExerciseName.REMEMBER_ALL -> WordCategory.Letters()
            ExerciseName.GAME_I_KNOW_5_NAMES -> WordCategory.Category()
            ExerciseName.THREE_LITER_JAR -> WordCategory.Letters()
            ExerciseName.LIST_OF_CATEGORIES -> WordCategory.Letters()
            ExerciseName.THREE_LETTERS -> WordCategory.Letters()
            ExerciseName.HALF -> WordCategory.Letters()
            ExerciseName.SPECIFICATIONS -> WordCategory.NounsNotAlive()
            ExerciseName.DICTIONARY_ADJECTIVES -> WordCategory.None()
            ExerciseName.DICTIONARY_NOUN -> WordCategory.None()
            ExerciseName.DICTIONARY_VERBS -> WordCategory.None()
            ExerciseName.LINGUISTIC_PYRAMIDS -> WordCategory.NounsNotAlive()
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> WordCategory.NounsAliveAndNotAlive()
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