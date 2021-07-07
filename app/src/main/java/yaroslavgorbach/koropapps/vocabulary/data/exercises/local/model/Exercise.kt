package yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import yaroslavgorbach.koropapps.vocabulary.R

enum class ExerciseName(@StringRes val id: Int) {
    ALPHABET(R.string.alphabet),
    MONOPHONIC(R.string.monophonic),
    NOUN(R.string.noun),
    VERBS(R.string.verbs),
    ANTONYMS_AND_SYNONYMS(R.string.antonyms_and_synonyms),
    TEN(R.string.ten),
    ASSOCIATIONS(R.string.associations),
    REMEMBER_ALL(R.string.remember_all),
    LISTS(R.string.lists),
    ADJECTIVES(R.string.adjectives),
}

data class Exercise(val name: ExerciseName, @DrawableRes val icon: Int)
