package yaroslavgorbach.koropapps.vocabulary.business.exercises.usecase

import yaroslavgorbach.koropapps.vocabulary.business.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class GetExerciseUseCase(val exercises: RepoExercises) {
    operator fun invoke(exerciseName: ExerciseName): Exercise {
        return exercises.getExercise(exerciseName)
    }
}