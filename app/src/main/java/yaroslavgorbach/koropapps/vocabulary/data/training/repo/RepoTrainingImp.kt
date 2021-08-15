package yaroslavgorbach.koropapps.vocabulary.data.training.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.dao.TrainingDao
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.ExerciseTrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercises
import java.util.*

class RepoTrainingImp(private val localDataSource: TrainingDao) : RepoTraining {

    override fun observe(): Observable<List<TrainingWithExercises>> {
        return localDataSource.observe()
    }

    override fun insert(trainingEntity: TrainingEntity): Completable {
        return localDataSource.insert(trainingEntity)
            .subscribeOn(Schedulers.io())
    }

}