package yaroslavgorbach.koropapps.vocabulary.data.description.repo

import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.DescriptionLocal
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

interface RepoDescription {
    fun getDescription(exerciseName: ExerciseName): DescriptionLocal
}