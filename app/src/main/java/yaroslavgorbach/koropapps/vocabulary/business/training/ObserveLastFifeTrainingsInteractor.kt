package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingFactory
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class ObserveLastFifeTrainingsInteractor(
    private val observeTrainingsInteractor: ObserveTrainingsInteractor,
    private val insertTrainingInteractor: InsertTrainingInteractor
) {
    operator fun invoke(): Observable<List<TrainingWithExercisesEntity>> {
        return observeTrainingsInteractor()
            .subscribeOn(Schedulers.io())
            .map { it.takeLast(5) }
            .doOnNext { trainings ->
                if (trainings.size < 5) {
                    insertTrainingInteractor(
                        TrainingFactory().create(TrainingFactory.TrainingType.TEST)
                    ).subscribe()
                }
            }
            .doOnNext { trainings ->
                if (trainings.size >= 5 && trainings.last().training.date.isToday().not()) {
                    insertTrainingInteractor(
                        TrainingFactory().create(TrainingFactory.TrainingType.TODAY)
                    ).subscribe()
                }
            }
    }
}