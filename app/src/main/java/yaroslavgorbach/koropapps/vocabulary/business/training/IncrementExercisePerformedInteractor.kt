package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class IncrementExercisePerformedInteractor(
    private val getTrainingExerciseInteractor: GetTrainingExerciseInteractor,
    private val updateTrainingExerciseInteractor: UpdateTrainingExerciseInteractor
) {
    operator fun invoke(exerciseId: Long): Completable {
        return getTrainingExerciseInteractor(exerciseId)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.isFinished.not()) {
                    it.performed++
                }
            }
            .flatMapCompletable(updateTrainingExerciseInteractor::invoke)
    }
}