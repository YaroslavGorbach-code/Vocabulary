package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercises
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining

class ObserveTrainingsInteractor(private val repoTraining: RepoTraining) {
    operator fun invoke(): Observable<List<TrainingWithExercises>> {
        return repoTraining.observe()
            .subscribeOn(Schedulers.io())
    }
}