package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining

class UpdateTrainingExerciseInteractor(private val repoTraining: RepoTraining) {
    operator fun invoke(trainingExerciseEntity: TrainingExerciseEntity): Completable {
        return repoTraining.updateExercise(trainingExerciseEntity)
            .subscribeOn(Schedulers.io())
    }
}