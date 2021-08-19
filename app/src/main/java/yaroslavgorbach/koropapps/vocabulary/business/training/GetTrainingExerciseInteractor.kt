package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining

class GetTrainingExerciseInteractor(private val repoTraining: RepoTraining) {
    operator fun invoke(exerciseId: Long): Single<TrainingExerciseEntity> {
        return repoTraining.getExercise(exerciseId)
            .subscribeOn(Schedulers.io())
    }
}