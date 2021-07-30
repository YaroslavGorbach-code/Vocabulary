package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.business.description.GetDescriptionUseCase
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExerciseUseCase
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescriptionImp
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp

@FlowPreview
class DescriptionVm : ViewModel() {
    private val getDescriptionUseCase = GetDescriptionUseCase(RepoDescriptionImp())
    private val getExerciseUseCase = GetExerciseUseCase(RepoExercisesImp())

    suspend fun getDescription(exerciseName: ExerciseName): Description {
        return getDescriptionUseCase(exerciseName)
    }

    fun getExerciseIconId(exerciseName: ExerciseName): Int {
        return getExerciseUseCase(exerciseName).icon
    }
}