package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

interface RepoExercises {
    suspend fun getExercises(): Flow<List<Exercise>>
    fun getExercise(exerciseName: ExerciseName): Exercise
}