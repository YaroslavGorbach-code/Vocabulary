package yaroslavgorbach.koropapps.vocabulary.business.exercises

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises

class GetExerciseUseCase(val exercises: RepoExercises) {
    operator fun invoke(exerciseName: ExerciseName): Exercise {
        return exercises.getExercise(exerciseName)
    }
}