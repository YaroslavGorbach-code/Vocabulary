package yaroslavgorbach.koropapps.vocabulary.business.training

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingExercisesFactory
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingFactory
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class ObserveCurrentTrainingWithExercisesInteractor(
    private val observeLastFifeTrainingsInteractor: ObserveLastFifeTrainingsInteractor,
    private val insertTrainingExercisesInteractor: InsertTrainingExercisesInteractor
) {
    operator fun invoke(): Observable<TrainingWithExercisesEntity> {
        return observeLastFifeTrainingsInteractor()
            .map { list ->
                var last = list.lastOrNull()
                if (last == null){
                    last = TrainingWithExercisesEntity(TrainingFactory().create(), emptyList())
                }
                last
            }
            .doOnNext { training ->
                if (training.exercises.isEmpty()) {
                    insertTrainingExercisesInteractor(
                        TrainingExercisesFactory().create(training.training)
                    ).subscribe()
                }
            }

    }
}