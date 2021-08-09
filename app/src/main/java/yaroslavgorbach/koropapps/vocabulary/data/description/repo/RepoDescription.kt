package yaroslavgorbach.koropapps.vocabulary.data.description.repo

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

interface RepoDescription {
    fun get(exerciseName: ExerciseName): Single<Description>
}