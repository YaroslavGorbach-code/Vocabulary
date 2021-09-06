package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class ExerciseNameToIconResMapper {
    fun map(exerciseName: ExerciseName): Int {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> R.drawable.ic_alphabet_a
            ExerciseName.ALPHABET_NOUN -> R.drawable.ic_alphabet_n
            ExerciseName.ALPHABET_VERBS -> R.drawable.ic_alphabet_v
            ExerciseName.TAUTOGRAMS -> R.drawable.ic_promotional_n
            ExerciseName.NARRATOR_NOUN -> R.drawable.ic_narrator_n
            ExerciseName.NARRATOR_ADJECTIVES -> R.drawable.ic_narrator_a
            ExerciseName.NARRATOR_VERBS -> R.drawable.ic_narrator_v
            ExerciseName.ANTONYMS_AND_SYNONYMS -> R.drawable.ic_arrows
            ExerciseName.TEN -> R.drawable.ic_ten
            ExerciseName.ASSOCIATIONS -> R.drawable.ic_assotiations
            ExerciseName.REMEMBER_ALL -> R.drawable.ic_memory
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.drawable.ic_game
            ExerciseName.THREE_LITER_JAR -> R.drawable.ic_jar
            ExerciseName.LIST_OF_CATEGORIES -> R.drawable.ic_share
        }
    }
}