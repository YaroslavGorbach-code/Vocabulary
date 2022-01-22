package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingExercisesFactory
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingFactory
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class ObservePreviousTrainingInteractor(
    private val observeTrainingsInteractor: ObserveTrainingsInteractor,
) {
    operator fun invoke(): Observable<TrainingWithExercisesEntity> {
        return observeTrainingsInteractor()
            .subscribeOn(Schedulers.io())
            .map(this::findPreviousTraining)
    }

    private fun findPreviousTraining(list: List<TrainingWithExercisesEntity>) =
        list.findLast { it.training.date.isToday().not() }
            ?: TrainingWithExercisesEntity(
                TrainingFactory().create(),
                TrainingExercisesFactory().create(TrainingFactory().create())
            )
}