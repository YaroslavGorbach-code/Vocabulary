package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingExercisesFactory
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class ObserveCurrentTrainingWithExercisesInteractor(
    private val observeTrainings: ObserveTrainingsInteractor,
    private val insertTrainingExercisesInteractor: InsertTrainingExercisesInteractor,
    private val finishTrainingInteractor: FinishTrainingInteractor
) {
    operator fun invoke(): Observable<TrainingWithExercisesEntity> {
        return observeTrainings()
            .subscribeOn(Schedulers.io())
            .map { it.last() }
            .doOnNext(this::createExercises)
            .doOnNext(this::checkTrainingOnFinish)
    }

    private fun createExercises(training: TrainingWithExercisesEntity) {
        if (training.exercises.isEmpty()) {
            insertTrainingExercisesInteractor(
                TrainingExercisesFactory().create(training.training)
            ).blockingSubscribe()
        }
    }

    private fun checkTrainingOnFinish(trainingWithExercises: TrainingWithExercisesEntity) {
        val isTrainingNeedToBeFinished =
            trainingWithExercises.training.isFinished.not() && trainingWithExercises.areAllTrainingExercisesFinished

        if (isTrainingNeedToBeFinished) {
            finishTrainingInteractor(trainingWithExercises.training).subscribe()
        }
    }
}