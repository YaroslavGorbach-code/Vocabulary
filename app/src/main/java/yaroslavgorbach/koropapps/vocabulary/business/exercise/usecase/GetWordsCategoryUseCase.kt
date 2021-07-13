package yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase

import yaroslavgorbach.koropapps.vocabulary.business.exercise.repo.RepoExercise

class GetWordsCategoryUseCase(val repo: RepoExercise) {
    operator fun invoke(): List<String> {
        return repo.getWordsCategory()
    }
}