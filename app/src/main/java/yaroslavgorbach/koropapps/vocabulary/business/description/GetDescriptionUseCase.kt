package yaroslavgorbach.koropapps.vocabulary.business.description.usecase

import yaroslavgorbach.koropapps.vocabulary.business.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class GetDescriptionUseCase(private val repoDescription: RepoDescription) {
    suspend operator fun invoke(exerciseName: ExerciseName): Description {
        return repoDescription.getDescription(exerciseName)
    }
}