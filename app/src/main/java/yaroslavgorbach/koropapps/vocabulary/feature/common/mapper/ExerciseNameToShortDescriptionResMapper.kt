package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import kotlin.random.Random

class ExerciseNameToShortDescriptionResMapper {
    fun map(exerciseName: ExerciseName): Int {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> R.string.desc_short_alphabet_a
            ExerciseName.ALPHABET_NOUN -> R.string.desc_short_alphabet_n
            ExerciseName.ALPHABET_VERBS -> R.string.desc_short_alphabet_n
            ExerciseName.TAUTOGRAMS -> R.string.desc_short_tautograms
            ExerciseName.NARRATOR_NOUN -> R.string.desc_short_narrator_noun
            ExerciseName.NARRATOR_ADJECTIVES -> R.string.desc_short_narrator_adjectives
            ExerciseName.NARRATOR_VERBS -> R.string.desc_short_narrator_verbs
            ExerciseName.ANTONYMS_AND_SYNONYMS -> when (Random.nextInt(1, 3)) {
                1 -> {
                    R.string.desc_short_antonyms
                }
                2 -> {
                    R.string.desc_short_synonyms
                }
                else -> {
                    0
                }
            }
            ExerciseName.TEN -> R.string.desc_short_ten
            ExerciseName.ASSOCIATIONS -> R.string.desc_short_associations
            ExerciseName.REMEMBER_ALL -> R.string.desc_short_remember_all
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.desc_short_game
            ExerciseName.THREE_LITER_JAR -> R.string.desc_short_three_liter_jar
            ExerciseName.LIST_OF_CATEGORIES -> R.string.desc_short_list_of_categories
            ExerciseName.THREE_LETTERS -> R.string.desc_short_three_letters
        }
    }
}