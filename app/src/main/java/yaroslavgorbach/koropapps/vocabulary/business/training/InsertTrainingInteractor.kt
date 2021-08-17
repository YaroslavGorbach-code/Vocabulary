package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining

class InsertTrainingInteractor(private val repoTraining: RepoTraining) {
    operator fun invoke(trainingEntity: TrainingEntity): Completable {
        return repoTraining.insertTraining(trainingEntity).subscribeOn(Schedulers.io())
    }
}