package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity

class FinishTrainingInteractor(private val insertTrainingInteractor: InsertTrainingInteractor) {
    operator fun invoke(trainingEntity: TrainingEntity): Completable {
        trainingEntity.isFinished = true
        return insertTrainingInteractor(trainingEntity).subscribeOn(Schedulers.io())

    }
}