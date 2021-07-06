package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import yaroslavgorbach.koropapps.vocabulary.business.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise

class RepoExercisesImp : RepoExercises {
    private val exercises: MutableStateFlow<List<Exercise>> = MutableStateFlow(emptyList())

    init {
        exercises.value = listOf(
            Exercise(1, "text 1", 0),
            Exercise(2, "text 2", 0),
            Exercise(3, "text 3", 0)
        )
    }

    override suspend fun getExercises(): Flow<List<Exercise>> {
        return exercises
    }
}