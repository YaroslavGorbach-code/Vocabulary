package yaroslavgorbach.koropapps.vocabulary.business.training

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.business.training.factory.TrainingFactory
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.utils.formatDefault
import yaroslavgorbach.koropapps.vocabulary.utils.isToday

class ObserveTrainingsInteractor(
    private val repoTraining: RepoTraining,
    private val insertTrainingInteractor: InsertTrainingInteractor
) {
    operator fun invoke(): Observable<List<TrainingWithExercisesEntity>> {
        return repoTraining.observeTrainingWithExercises()
            .subscribeOn(Schedulers.io())
            .doOnNext(this::createTodayTraining)
            .map { trainings -> trainings.filter { it.training.date != null } }
    }

    private fun createTodayTraining(trainings: List<TrainingWithExercisesEntity>) {
        Log.i("dcscfs", trainings.toString())

        if (trainings.lastOrNull()?.training?.date.isToday().not()) {
            Log.i("dcscfs", "in")
            Log.i("dcscfs", trainings.lastOrNull()?.training?.date?.formatDefault().toString())
            insertTrainingInteractor(
                TrainingFactory().create(
                    TrainingFactory.TrainingType.TODAY,
                    previousTraining = trainings.lastOrNull()?.training
                        ?: TrainingFactory().create()
                )
            ).blockingSubscribe()
        }
    }
}