package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Single

class GetCurrentTrainingIsFinishedInteractor(
    private val observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor
) {
    operator fun invoke(): Single<Boolean> {
        return observeCurrentTrainingWithExercisesInteractor().firstOrError()
            .map { it.training.isFinished }
    }
}