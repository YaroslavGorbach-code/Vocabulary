package yaroslavgorbach.koropapps.vocabulary.business.exercises

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi

class GetExerciseIntearactor(val exercises: RepoExercises) {
    operator fun invoke(exerciseName: ExerciseName): Single<Exercise> {
        return exercises.get(exerciseName)
    }
}