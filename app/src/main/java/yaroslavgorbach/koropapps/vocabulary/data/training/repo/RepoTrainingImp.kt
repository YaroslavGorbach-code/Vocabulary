package yaroslavgorbach.koropapps.vocabulary.data.training.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class RepoTrainingImp(private val localDataSource: TrainingDao) : RepoTraining {

    override fun observe(): Observable<List<TrainingWithExercisesEntity>> {
        return localDataSource.observe()
    }

    override fun insertTraining(trainingEntity: TrainingEntity): Completable {
        return localDataSource.insertTraining(trainingEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun insertExercises(trainingExercises: List<TrainingExerciseEntity>): Completable {
        return localDataSource.insertExercises(trainingExercises).subscribeOn(Schedulers.io())
    }

}