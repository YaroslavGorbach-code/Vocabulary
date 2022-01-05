package yaroslavgorbach.koropapps.vocabulary.data.training.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class RepoTrainingImp(private val localDataSource: TrainingDao) : RepoTraining {

    override fun observeTrainingWithExercises(): Observable<List<TrainingWithExercisesEntity>> {
        return localDataSource.observeTrainingWithExercisesEntity()
            .subscribeOn(Schedulers.io())
    }

    override fun observeExercise(exerciseId: Long): Observable<TrainingExerciseEntity> {
        return localDataSource.observeExercise(exerciseId)
            .subscribeOn(Schedulers.io())
    }

    override fun getExercise(exerciseId: Long): Single<TrainingExerciseEntity> {
        return localDataSource.getExercise(exerciseId)
            .subscribeOn(Schedulers.io())
    }

    override fun insertTraining(trainingEntity: TrainingEntity): Completable {
        return localDataSource.insertTraining(trainingEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun insertExercises(trainingExercises: List<TrainingExerciseEntity>): Completable {
        return localDataSource.insertExercises(trainingExercises)
            .subscribeOn(Schedulers.io())
    }

    override fun updateExercise(trainingExercise: TrainingExerciseEntity): Completable {
        return localDataSource.updateExercise(trainingExercise)
            .subscribeOn(Schedulers.io())
    }

    override fun clearTrainings(): Completable {
        return localDataSource.deleteAllTrainings()
            .andThen(localDataSource.deleteAllTrainingExercises())
            .subscribeOn(Schedulers.io())
    }
}