package yaroslavgorbach.koropapps.vocabulary.business.exercise

import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExercise

class GetLettersUseCase(private val repoExercise: RepoExercise) {
    operator fun invoke(): List<String> {
        return repoExercise.getLetters()
    }
}