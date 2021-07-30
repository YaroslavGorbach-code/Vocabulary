package yaroslavgorbach.koropapps.vocabulary.business.exercise

import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExercise

class GetWordsCategoryUseCase(val repo: RepoExercise) {
    operator fun invoke(): List<String> {
        return repo.getWordsCategory()
    }
}