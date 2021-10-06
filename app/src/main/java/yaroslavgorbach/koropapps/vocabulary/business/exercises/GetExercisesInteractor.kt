package yaroslavgorbach.koropapps.vocabulary.business.exercises

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises

class GetExercisesInteractor(private val repoExercises: RepoExercises) {
    operator fun invoke(): Flow<List<Exercise>> {
        return repoExercises.observe()
    }
}