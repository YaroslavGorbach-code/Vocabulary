package yaroslavgorbach.koropapps.vocabulary.business.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.vocabulary.local.model.Exercise

interface Repo {
    suspend fun getExercises(): Flow<List<Exercise>>
}