package yaroslavgorbach.koropapps.vocabulary.business.usecase

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.business.repo.Repo
import yaroslavgorbach.koropapps.vocabulary.data.vocabulary.local.model.Exercise

class GetExercisesUseCase(private val repo: Repo) {
   suspend operator fun invoke(): Flow<List<Exercise>> {
        return repo.getExercises()
    }
}