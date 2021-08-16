package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class ObserveCurrentTrainingWithExercisesInteractor(
    private val observeLastFifeTrainingsInteractor: ObserveLastFifeTrainingsInteractor
) {
    operator fun invoke(): Observable<TrainingWithExercisesEntity> {
        return observeLastFifeTrainingsInteractor()
            .map { list -> list.last() }
    }
}