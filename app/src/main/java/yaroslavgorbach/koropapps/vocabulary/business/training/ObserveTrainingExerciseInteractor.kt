package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining

class ObserveTrainingExerciseInteractor(private val repoTraining: RepoTraining) {
    operator fun invoke(exerciseId: Long): Observable<TrainingExerciseEntity> {
        return repoTraining.observeExercise(exerciseId)
    }
}