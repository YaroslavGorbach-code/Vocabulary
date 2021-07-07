package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.business.description.usecase.GetDescriptionUseCase
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescriptionImp
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

@FlowPreview
class DescriptionVm : ViewModel() {
    private val getDescriptionUseCase = GetDescriptionUseCase(RepoDescriptionImp())

    suspend fun getDescription(exerciseName: ExerciseName): Description {
        return getDescriptionUseCase(exerciseName)
    }
}