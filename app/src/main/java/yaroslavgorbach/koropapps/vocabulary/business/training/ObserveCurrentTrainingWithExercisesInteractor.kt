package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingExercisesFactory
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class ObserveCurrentTrainingWithExercisesInteractor(
    private val observeLastFifeTrainingsInteractor: ObserveLastFifeTrainingsInteractor,
    private val insertTrainingExercisesInteractor: InsertTrainingExercisesInteractor
) {
    operator fun invoke(): Observable<TrainingWithExercisesEntity> {
        return observeLastFifeTrainingsInteractor()
            .map { list -> list.last() }
            .doOnNext { training ->
                if (training.exercises.isEmpty()) {
                    insertTrainingExercisesInteractor(
                        TrainingExercisesFactory().create(training.training)
                    ).subscribe()
                }
            }
    }
}