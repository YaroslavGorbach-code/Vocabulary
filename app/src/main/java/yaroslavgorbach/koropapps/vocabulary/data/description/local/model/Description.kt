package yaroslavgorbach.koropapps.vocabulary.data.description.local.model

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper

data class Description(val exerciseName: ExerciseName) {

    val exerciseIconRes: Int
        get() = ExerciseNameToIconResMapper().map(exerciseName)

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
            ExerciseName.LIST_OF_CATEGORIES -> R.string.lists_of_categories_description
            ExerciseName.THREE_LETTERS -> R.string.three_letters_description
            ExerciseName.HALF -> R.string.half_description
            ExerciseName.SPECIFICATIONS -> R.string.specifications_description
        }
}
