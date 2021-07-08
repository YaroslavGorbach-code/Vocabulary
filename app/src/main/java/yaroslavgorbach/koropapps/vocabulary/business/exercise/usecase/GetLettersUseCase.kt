package yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase

import yaroslavgorbach.koropapps.vocabulary.business.exercise.alphabet.model.Letter
import yaroslavgorbach.koropapps.vocabulary.business.exercise.repo.RepoExercise

class GetLettersUseCase(private val repoExercise: RepoExercise) {
    operator fun invoke(): List<Letter>{
       return repoExercise.getLetters()
    }
}