package yaroslavgorbach.koropapps.vocabulary.business.exercises

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises

class ObserveExerciseInteractor(private val repoExercises: RepoExercises) {

    suspend operator fun invoke(exerciseName: ExerciseName): Flow<Exercise> {
        return repoExercises.get(exerciseName)
    }
}