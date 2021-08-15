package yaroslavgorbach.koropapps.vocabulary.business.training

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercises
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingFactory
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class ObserveLastFifeTrainingsInteractor(
    private val observeTrainingsInteractor: ObserveTrainingsInteractor,
    private val insertTrainingInteractor: InsertTrainingInteractor
) {
    operator fun invoke(): Observable<List<TrainingWithExercises>> {
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
                if (trainings.size >= 5 && trainings.last().trainingEntity.date.isToday().not()) {
                    insertTrainingInteractor(
                        TrainingFactory().create(TrainingFactory.TrainingType.TODAY)
                    ).subscribe()
                }
                Log.i("rororo", trainings.last().trainingEntity.date.isToday().toString())
            }
    }
}