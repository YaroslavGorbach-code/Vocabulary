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
            ExerciseName.LIST_OF_CATEGORIES -> R.drawable.ic_lists
            ExerciseName.THREE_LETTERS -> R.drawable.ic_letters
            ExerciseName.HALF -> R.drawable.ic_half
            ExerciseName.SPECIFICATIONS -> R.drawable.ic_spetifications
            ExerciseName.DICTIONARY_ADJECTIVES -> R.drawable.ic_dictionary_a
            ExerciseName.DICTIONARY_NOUN -> R.drawable.ic_dictionary_n
            ExerciseName.DICTIONARY_VERBS -> R.drawable.ic_dictionary_v
            ExerciseName.LINGUISTIC_PYRAMIDS -> R.drawable.ic_piramids
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> R.drawable.ic_raven
            ExerciseName.STORYTELLER_IMPROVISER -> R.drawable.ic_talcking_man
            ExerciseName.ADVANCED_BINDING -> R.drawable.ic_chain
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT -> R.drawable.ic_person_with_microfon
            ExerciseName.OTHER_ABBREVIATIONS -> R.drawable.ic_letter
            ExerciseName.MAGIC_NAMING -> R.drawable.ic_magic
            ExerciseName.BUYING_SELLING -> R.drawable.ic_cart
            ExerciseName.CO_AUTHORED_WITH_DAHL -> R.drawable.ic_pan
            ExerciseName.RORSCHACH_TEST -> R.drawable.ic_butterfly
            ExerciseName.WILL_NOT_BE_WORSE -> R.drawable.ic_thomb_down
            ExerciseName.QUESTION_ANSWER -> R.drawable.ic_qestion
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS -> R.drawable.ic_raven_2
            ExerciseName.TONGUE_TWISTERS_EASY -> R.drawable.ic_lips_green
            ExerciseName.TONGUE_TWISTERS_HARD -> R.drawable.ic_lips_yellow
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> R.drawable.ic_lips_read
            ExerciseName.SOUND_COMBINATIONS -> R.drawable.ic_notes
            ExerciseName.DIFFICULT_WORDS -> R.drawable.ic_knot
            ExerciseName.COUP_OF_CONSCIOUSNESS -> R.drawable.ic_coup_of_consciousness
            ExerciseName.PROBLEM_SOLVING -> R.drawable.ic_problem_solving
            ExerciseName.COUP -> R.drawable.ic_coup
        }
    }
}