package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.Training
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class GetTodayTrainingInteractor(private val observeTrainingsInteractor: ObserveTrainingsInteractor) {
    operator fun invoke(): Single<Training> {
        return observeTrainingsInteractor()
            .map { list -> list.first { it.date.isToday() } }
            .firstOrError()
    }
}

