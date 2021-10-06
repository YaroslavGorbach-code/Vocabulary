package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

interface RepoExercises {
    fun observe(): Flow<List<Exercise>>

    suspend fun changeFavorite(exercise: Exercise)

    suspend fun get(exerciseName: ExerciseName): Flow<Exercise>
}