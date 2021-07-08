package yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import yaroslavgorbach.koropapps.vocabulary.R

enum class ExerciseName(@StringRes val id: Int) {
    ALPHABET_ADJECTIVES(R.string.alphabet_adjectives),
    ALPHABET_NOUN(R.string.alphabet_noun),
    ALPHABET_VERBS(R.string.alphabet_verbs),
    TAUTOGRAMS(R.string.tautograms),
    NOUN(R.string.noun),
    VERBS(R.string.verbs),
    ANTONYMS_AND_SYNONYMS(R.string.antonyms_and_synonyms),
    TEN(R.string.ten),
    ASSOCIATIONS(R.string.associations),
    REMEMBER_ALL(R.string.remember_all),
    LISTS(R.string.lists),
    ADJECTIVES(R.string.adjectives),
    GAME_I_KNOW_5_NAMES(R.string.game_i_know_5_names),
    THREE_LITER_JAR(R.string.three_liter_jar)
}

data class Exercise(val name: ExerciseName, @DrawableRes val icon: Int)
