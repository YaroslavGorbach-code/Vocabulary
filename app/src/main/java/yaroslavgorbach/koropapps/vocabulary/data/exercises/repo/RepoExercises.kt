package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi

interface RepoExercises {
    fun get(): Single<List<Exercise>>
    fun get(exerciseName: ExerciseName): Single<Exercise>
}