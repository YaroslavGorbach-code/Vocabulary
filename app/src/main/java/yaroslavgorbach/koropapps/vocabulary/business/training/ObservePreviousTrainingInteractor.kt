package yaroslavgorbach.koropapps.vocabulary.business.training

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class ObservePreviousTrainingInteractor(
    private val observeTrainingsInteractor: ObserveTrainingsInteractor,
) {
    operator fun invoke(): Observable<TrainingWithExercisesEntity?> {
        return observeTrainingsInteractor()
            .map { list -> list.findLast { it.training.date.isToday().not() } }
    }
}