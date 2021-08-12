package yaroslavgorbach.koropapps.vocabulary.data.training.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercises

interface RepoTraining {
    fun observe(): Observable<List<TrainingWithExercises>>

    fun insert(trainingEntity: TrainingEntity): Completable
}