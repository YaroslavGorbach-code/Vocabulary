package yaroslavgorbach.koropapps.vocabulary.business.exercises.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise

interface RepoExercises {
    suspend fun getExercises(): Flow<List<Exercise>>
}