package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class ExerciseNameToDescriptionResMapper {

    fun map(exerciseName: ExerciseName): Int {
        return when (exerciseName) {
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
            ExerciseName.DICTIONARY_ADJECTIVES -> R.string.dictionary_adjectives_description
            ExerciseName.DICTIONARY_NOUN -> R.string.dictionary_nouns_description
            ExerciseName.DICTIONARY_VERBS -> R.string.dictionary_verbs_description
            ExerciseName.LINGUISTIC_PYRAMIDS -> R.string.linguistic_pyramids_description
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> R.string.raven_look_like_a_table_description
            ExerciseName.STORYTELLER_IMPROVISER -> R.string.storyteller_improviser_description
            ExerciseName.ADVANCED_BINDING -> R.string.advanced_binding_description
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT -> R.string.what_i_see_i_sing_about_description
            ExerciseName.OTHER_ABBREVIATIONS -> R.string.other_abbreviations_description
            ExerciseName.MAGIC_NAMING -> R.string.magic_naming_description
            ExerciseName.BUYING_SELLING -> R.string.buying_selling_description
            ExerciseName.CO_AUTHORED_WITH_DAHL -> R.string.co_authored_with_dahl_description
            ExerciseName.RORSCHACH_TEST -> R.string.rorschach_test_description
            ExerciseName.WILL_NOT_BE_WORSE -> R.string.will_not_be_worse_description
            ExerciseName.QUESTION_ANSWER -> R.string.question_answer_description
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS -> R.string.raven_look_like_a_table_filings_description
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.string.tongue_twisters_description
            ExerciseName.SOUND_COMBINATIONS -> R.string.sound_combinations_description
            ExerciseName.DIFFICULT_WORDS -> R.string.difficult_words_description
            ExerciseName.COUP_OF_CONSCIOUSNESS -> R.string.coup_of_consciousness_description
            ExerciseName.PROBLEM_SOLVING -> R.string.problem_solving_description
        }
    }
}