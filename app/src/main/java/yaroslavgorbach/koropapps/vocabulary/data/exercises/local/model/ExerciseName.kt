package yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model

import androidx.annotation.StringRes
import yaroslavgorbach.koropapps.vocabulary.R

enum class ExerciseName(@StringRes val id: Int) {
    ALPHABET_ADJECTIVES(R.string.alphabet_adjectives),
    ALPHABET_NOUN(R.string.alphabet_noun),
    ALPHABET_VERBS(R.string.alphabet_verbs),
    TAUTOGRAMS(R.string.tautograms),
    NARRATOR_NOUN(R.string.narrator_noun),
    NARRATOR_ADJECTIVES(R.string.narrator_adjectives),
    NARRATOR_VERBS(R.string.narrator_verbs),
    ANTONYMS_AND_SYNONYMS(R.string.antonyms_and_synonyms),
    TEN(R.string.ten),
    ASSOCIATIONS(R.string.associations),
    REMEMBER_ALL(R.string.remember_all),
    GAME_I_KNOW_5_NAMES(R.string.game_i_know_5_names),
    THREE_LITER_JAR(R.string.three_liter_jar),
    LIST_OF_CATEGORIES(R.string.list_of_categories),
    THREE_LETTERS(R.string.three_letters),
    HALF(R.string.half),
    SPECIFICATIONS(R.string.specifications),
    DICTIONARY_ADJECTIVES(R.string.dictionary_adjectives),
    DICTIONARY_NOUN(R.string.dictionary_noun),
    DICTIONARY_VERBS(R.string.dictionary_verbs),

    LINGUISTIC_PYRAMIDS(R.string.linguistic_pyramids),
    RAVEN_LOOK_LIKE_A_TABLE(R.string.raven_look_like_a_table),
    STORYTELLER_IMPROVISER(R.string.storyteller_improviser),
    ADVANCED_BINDING(R.string.advanced_binding),
    WHAT_I_SEE_I_SING_ABOUT(R.string.what_i_see_i_sing_about),
    OTHER_ABBREVIATIONS(R.string.other_abbreviations),
    MAGIC_NAMING(R.string.magic_naming),
    BUYING_SELLING(R.string.buying_selling),
    CO_AUTHORED_WITH_DAHL(R.string.co_authored_with_dahl),
    RORSCHACH_TEST(R.string.rorschach_test),
    WILL_NOT_BE_WORSE(R.string.will_not_be_worse),
    QUESTION_ANSWER(R.string.question_answer),
    RAVEN_LOOK_LIKE_A_TABLE_FILINGS(R.string.raven_look_like_a_table_filings),

    TONGUE_TWISTERS_EASY(R.string.tongue_twisters_easy),
    TONGUE_TWISTERS_HARD(R.string.tongue_twisters_hard),
    TONGUE_TWISTERS_VERY_HARD(R.string.tongue_twisters_very_hard),
    SOUND_COMBINATIONS(R.string.sound_combinations),
    DIFFICULT_WORDS(R.string.difficult_words)
}