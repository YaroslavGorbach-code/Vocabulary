package yaroslavgorbach.koropapps.vocabulary.business.exercises

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi

class GetExercisesInteract(private val repoExercises: RepoExercises) {
    operator fun invoke(): Single<List<Exercise>> {
        return repoExercises.get()
    }
}