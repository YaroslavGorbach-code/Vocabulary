package yaroslavgorbach.koropapps.vocabulary.business.description

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class GetDescriptionUseCase(private val repoDescription: RepoDescription) {
    operator fun invoke(exerciseName: ExerciseName): Single<Description> {
        return repoDescription.get(exerciseName)
    }
}