package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.business.description.GetDescriptionUseCase
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExerciseUseCase
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.DescriptionLocal
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescriptionImp
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp

@FlowPreview
class DescriptionVm : ViewModel() {
    private val repoExercises: RepoExercises
        get() = RepoExercisesImp()

    private val repoDescription: RepoDescription
        get() = RepoDescriptionImp(getExerciseUseCase)

    private val getDescriptionUseCase: GetDescriptionUseCase
        get() = GetDescriptionUseCase(repoDescription)

    private val getExerciseUseCase: GetExerciseUseCase
        get() = GetExerciseUseCase(repoExercises)

    fun getDescription(exerciseName: ExerciseName): DescriptionLocal {
        return getDescriptionUseCase(exerciseName)
    }
}