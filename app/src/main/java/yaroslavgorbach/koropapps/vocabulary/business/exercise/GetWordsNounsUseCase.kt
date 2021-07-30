package yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase

import yaroslavgorbach.koropapps.vocabulary.business.exercise.repo.RepoExercise

class GetWordsNounsUseCase(private val repoExercise: RepoExercise) {
    operator fun invoke(): List<String> {
        return repoExercise.getWordsNouns()
    }
}