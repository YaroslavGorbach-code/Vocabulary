package yaroslavgorbach.koropapps.vocabulary.data.description.local.model

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

data class Description(val exerciseName: ExerciseName) {

    val exerciseIconRes: Int
        get() = when (exerciseName) {
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
            ExerciseName.REMEMBER_ALL -> R.drawable.ic_test
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.drawable.ic_game
            ExerciseName.THREE_LITER_JAR -> R.drawable.ic_jar
        }

    val textRes: Int
        get() = when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> R.string.alphabet_adjectives_description
            ExerciseName.ALPHABET_NOUN -> R.string.alphabet_noun_description
            ExerciseName.ALPHABET_VERBS -> R.string.alphabet_verbs_description
            ExerciseName.TAUTOGRAMS -> R.string.tautograms_description
            ExerciseName.NARRATOR_NOUN -> R.string.narrator_noun_description
            ExerciseName.NARRATOR_ADJECTIVES -> R.string.narrator_adjectives_description
            ExerciseName.NARRATOR_VERBS -> R.string.narrator_verbs_description
            ExerciseName.ANTONYMS_AND_SYNONYMS -> R.string.antonyms_and_synonyms_description
            ExerciseName.TEN -> R.string.ten_description
            ExerciseName.ASSOCIATIONS -> R.string.associations_description
            ExerciseName.REMEMBER_ALL -> R.string.remember_all_description
            ExerciseName.GAME_I_KNOW_5_NAMES -> R.string.game_i_know_5_names_description
            ExerciseName.THREE_LITER_JAR -> R.string.three_liter_jar_description
        }
}
