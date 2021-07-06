package yaroslavgorbach.koropapps.vocabulary.data.vocabulary.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import yaroslavgorbach.koropapps.vocabulary.business.repo.Repo
import yaroslavgorbach.koropapps.vocabulary.data.vocabulary.local.model.Exercise

class RepoImp : Repo {
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