package yaroslavgorbach.koropapps.vocabulary.data.training.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.Training

interface RepoTraining {
    fun observe(): Observable<List<Training>>
    fun insert(training: Training): Completable
    fun insert(trainings: List<Training>): Completable
}