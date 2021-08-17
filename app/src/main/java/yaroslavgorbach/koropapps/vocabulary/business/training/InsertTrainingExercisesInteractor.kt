package yaroslavgorbach.koropapps.vocabulary.business.training

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining

class InsertTrainingExercisesInteractor(private val repoTraining: RepoTraining) {
    operator fun invoke(exercises: List<TrainingExerciseEntity>): Completable {
        return repoTraining.insertExercises(exercises).subscribeOn(Schedulers.io())
    }
}