package yaroslavgorbach.koropapps.vocabulary.business.exercise.repo

import yaroslavgorbach.koropapps.vocabulary.business.exercise.alphabet.model.Letter

interface RepoExercise {
    fun getLetters(): List<Letter>
}