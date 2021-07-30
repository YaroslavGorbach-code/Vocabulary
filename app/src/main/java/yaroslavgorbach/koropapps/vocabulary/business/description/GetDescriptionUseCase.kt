package yaroslavgorbach.koropapps.vocabulary.business.description

import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.DescriptionLocal
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class GetDescriptionUseCase(private val repoDescription: RepoDescription) {
    operator fun invoke(exerciseName: ExerciseName): DescriptionLocal {
        return repoDescription.getDescription(exerciseName)
    }
}