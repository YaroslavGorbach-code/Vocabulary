package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp

class ExercisesListVm : ViewModel() {

    private val exercisesRepo: RepoExercises
        get() = RepoExercisesImp()

    private val getExercisesUseCase: GetExercisesUseCase
        get() = GetExercisesUseCase(exercisesRepo)

    suspend fun getExercises(): Flow<List<Exercise>> {
        return getExercisesUseCase()
    }

}