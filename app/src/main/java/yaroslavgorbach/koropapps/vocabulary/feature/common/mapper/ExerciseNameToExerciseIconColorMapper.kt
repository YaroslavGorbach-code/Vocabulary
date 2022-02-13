package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import android.content.Context
import android.graphics.Color
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class ExerciseNameToExerciseIconColorMapper(val context: Context) {

    fun map(exerciseName: ExerciseName): Int {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> Color.parseColor("#ec6633")
            ExerciseName.ALPHABET_NOUN -> Color.parseColor("#424242")
            ExerciseName.ALPHABET_VERBS -> Color.parseColor("#f9b54d")
            ExerciseName.TAUTOGRAMS -> Color.parseColor("#e2e2e1")
            ExerciseName.NARRATOR_NOUN -> Color.parseColor("#ffa02e")
            ExerciseName.NARRATOR_ADJECTIVES -> Color.parseColor("#ba73ae")
            ExerciseName.NARRATOR_VERBS -> Color.parseColor("#0aa9de")
            ExerciseName.ANTONYMS_AND_SYNONYMS -> Color.parseColor("#f0c216")
            ExerciseName.TEN -> Color.parseColor("#ffce00")
            ExerciseName.ASSOCIATIONS -> Color.parseColor("#b222b2")
            ExerciseName.REMEMBER_ALL -> Color.parseColor("#439f46")
            ExerciseName.GAME_I_KNOW_5_NAMES -> Color.parseColor("#fc6f58")
            ExerciseName.THREE_LITER_JAR -> Color.parseColor("#00aead")
            ExerciseName.LIST_OF_CATEGORIES -> Color.parseColor("#473080")
            ExerciseName.THREE_LETTERS -> Color.parseColor("#9e209e")
            ExerciseName.HALF -> Color.parseColor("#939393")
            ExerciseName.SPECIFICATIONS -> Color.parseColor("#273b7a")
            ExerciseName.DICTIONARY_ADJECTIVES -> Color.parseColor("#bcaaa4")
            ExerciseName.DICTIONARY_NOUN -> Color.parseColor("#f18767")
            ExerciseName.DICTIONARY_VERBS -> Color.parseColor("#84c59e")
            ExerciseName.LINGUISTIC_PYRAMIDS -> Color.parseColor("#9d6846")
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> Color.parseColor("#ffee5a")
            ExerciseName.STORYTELLER_IMPROVISER -> Color.parseColor("#47b39c")
            ExerciseName.ADVANCED_BINDING -> Color.parseColor("#8f4e99")
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT -> Color.parseColor("#bcaaa4")
            ExerciseName.OTHER_ABBREVIATIONS -> Color.parseColor("#90a4ae")
            ExerciseName.MAGIC_NAMING -> Color.parseColor("#3d5afe")
            ExerciseName.BUYING_SELLING -> Color.parseColor("#ffa726")
            ExerciseName.CO_AUTHORED_WITH_DAHL -> Color.parseColor("#22a69a")
            ExerciseName.RORSCHACH_TEST -> Color.parseColor("#ccd73e")
            ExerciseName.WILL_NOT_BE_WORSE -> Color.parseColor("#e9407a")
            ExerciseName.QUESTION_ANSWER -> Color.parseColor("#546e7a")
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS -> Color.parseColor("#29b6f6")
            ExerciseName.TONGUE_TWISTERS_EASY -> Color.parseColor("#64dd17")
            ExerciseName.TONGUE_TWISTERS_HARD -> Color.parseColor("#ffd600")
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> Color.parseColor("#ef5350")
            ExerciseName.SOUND_COMBINATIONS -> Color.parseColor("#5b5b5b")
            ExerciseName.DIFFICULT_WORDS -> Color.parseColor("#ffcf35")
            ExerciseName.COUP_OF_CONSCIOUSNESS -> Color.parseColor("#772877")
            ExerciseName.PROBLEM_SOLVING -> Color.parseColor("#11bfb2")
            ExerciseName.COUP -> Color.parseColor("#ff6700")
        }
    }
}