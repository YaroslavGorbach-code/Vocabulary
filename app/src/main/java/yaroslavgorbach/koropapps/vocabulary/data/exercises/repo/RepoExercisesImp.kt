package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.ExercisesDataStore
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class RepoExercisesImp(private val localDataStore: ExercisesDataStore, private val context: Context) : RepoExercises {

    override fun observe(): Flow<List<Exercise>> {
        return localDataStore.observe(context)
    }

    override suspend fun changeFavorite(exercise: Exercise) {
        localDataStore.changeFavorite(context = context, exercise = exercise)
    }

    override suspend fun get(exerciseName: ExerciseName): Flow<Exercise> {
        return localDataStore.get(context, exerciseName)
    }
}