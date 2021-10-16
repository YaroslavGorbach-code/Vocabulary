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
            ExerciseName.HALF -> R.string.desc_short_half
            ExerciseName.SPECIFICATIONS -> R.string.desc_short_specifications
            ExerciseName.DICTIONARY_ADJECTIVES -> R.string.desc_short_dictionary_adjectives
            ExerciseName.DICTIONARY_NOUN -> R.string.desc_short_dictionary_nouns
            ExerciseName.DICTIONARY_VERBS -> R.string.desc_short_dictionary_verbs
            ExerciseName.LINGUISTIC_PYRAMIDS -> when (Random.nextInt(1, 4)) {
                1 -> {
                    R.string.desc_short_linguistic_pyramids_1
                }
                2 -> {
                    R.string.desc_short_linguistic_pyramids_2
                }
                3 -> {
                    R.string.desc_short_linguistic_pyramids_3
                }
                else -> {
                    0
                }
            }
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> R.string.desc_short_raven_look_like_a_table
            ExerciseName.STORYTELLER_IMPROVISER -> R.string.desc_short_storyteller_improviser
            ExerciseName.ADVANCED_BINDING -> R.string.desc_short_advanced_binding
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT -> R.string.desc_short_what_i_see_i_sing_about
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.desc_short_other_abbreviations
            ExerciseName.MAGIC_NAMING -> R.string.desc_short_magic_naming
            ExerciseName.BUYING_SELLING -> R.string.desc_short_buying_selling
            ExerciseName.CO_AUTHORED_WITH_DAHL -> R.string.desc_short_co_authored_with_dahl
            ExerciseName.RORSCHACH_TEST -> R.string.desc_short_rorschach_test
            ExerciseName.WILL_NOT_BE_WORSE -> R.string.desc_short_will_not_be_worse
            ExerciseName.QUESTION_ANSWER -> R.string.desc_short_question_answer
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS -> R.string.desc_short_raven_look_like_a_table_filings
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.array.desc_short_tongue_twisters
            ExerciseName.SOUND_COMBINATIONS -> R.array.desc_short_sound_combinations
        }
    }
}