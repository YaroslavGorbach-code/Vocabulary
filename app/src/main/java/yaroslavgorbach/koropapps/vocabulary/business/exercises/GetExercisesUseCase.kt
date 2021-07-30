package yaroslavgorbach.koropapps.vocabulary.business.exercises

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise

class GetExercisesUseCase(private val repoExercises: RepoExercises) {
   suspend operator fun invoke(): Flow<List<Exercise>> {
        return repoExercises.getExercises()
    }
}