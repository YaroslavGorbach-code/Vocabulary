package yaroslavgorbach.koropapps.vocabulary.data.exercise.repo

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.alphabet.model.Letter
import yaroslavgorbach.koropapps.vocabulary.business.exercise.repo.RepoExercise

class RepoExerciseImp: RepoExercise {
    private val letters = listOf(Letter(R.string.letter_1), Letter(R.string.letter_2),
        Letter(R.string.letter_3), Letter(R.string.letter_4),
        Letter(R.string.letter_5), Letter(R.string.letter_6),
        Letter(R.string.letter_7), Letter(R.string.letter_8),
        Letter(R.string.letter_9), Letter(R.string.letter_10),
        Letter(R.string.letter_11), Letter(R.string.letter_12),
        Letter(R.string.letter_13), Letter(R.string.letter_14),
        Letter(R.string.letter_15), Letter(R.string.letter_16),
        Letter(R.string.letter_17), Letter(R.string.letter_18),
        Letter(R.string.letter_19), Letter(R.string.letter_20),
        Letter(R.string.letter_21), Letter(R.string.letter_22),
        Letter(R.string.letter_23), Letter(R.string.letter_24),
        Letter(R.string.letter_25), Letter(R.string.letter_26),
        Letter(R.string.letter_27), Letter(R.string.letter_28),
        Letter(R.string.letter_29), Letter(R.string.letter_30),
        )
    override fun getLetters(): List<Letter> {
        return letters
    }
}