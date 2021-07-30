package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp

class ExercisesListVm : ViewModel() {
    private val getExercisesUseCase = GetExercisesUseCase(RepoExercisesImp())

    suspend fun getExercises(): Flow<List<Exercise>>{
        return getExercisesUseCase()
    }
}