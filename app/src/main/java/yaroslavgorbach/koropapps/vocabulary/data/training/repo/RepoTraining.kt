package yaroslavgorbach.koropapps.vocabulary.data.training.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

interface RepoTraining {
    fun observe(): Observable<List<TrainingWithExercisesEntity>>

    fun insertTraining(trainingEntity: TrainingEntity): Completable

    fun insertExercises(trainingExercises: List<TrainingExerciseEntity>): Completable
}