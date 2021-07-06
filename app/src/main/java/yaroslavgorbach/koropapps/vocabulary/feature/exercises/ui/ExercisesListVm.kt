package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.usecase.GetExercisesUseCase
import yaroslavgorbach.koropapps.vocabulary.data.vocabulary.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.vocabulary.repo.RepoImp

class ExercisesListVm : ViewModel() {
    private val getExercisesUseCase = GetExercisesUseCase(RepoImp())

    suspend fun getExercises(): Flow<List<Exercise>>{
        return getExercisesUseCase()
    }
}