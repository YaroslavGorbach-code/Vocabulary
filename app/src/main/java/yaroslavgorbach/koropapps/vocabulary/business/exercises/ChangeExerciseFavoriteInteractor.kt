package yaroslavgorbach.koropapps.vocabulary.business.exercises

import kotlinx.coroutines.flow.collect
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises

class ChangeExerciseFavoriteInteractor(private val repoExercises: RepoExercises) {

    suspend operator fun invoke(exerciseName: ExerciseName) {
        repoExercises.get(exerciseName).collect { exercise ->
            exercise.isFavorite = exercise.isFavorite.not()

            repoExercises.changeFavorite(exercise)
        }
    }
}