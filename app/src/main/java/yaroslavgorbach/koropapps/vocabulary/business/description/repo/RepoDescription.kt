package yaroslavgorbach.koropapps.vocabulary.business.description.repo

import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

interface RepoDescription {
    suspend fun getDescription(exerciseName: ExerciseName): Description
}