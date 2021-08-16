package yaroslavgorbach.koropapps.vocabulary.feature.training.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.training.InsertTrainingInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveCurrentTrainingWithExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveLastFifeTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.training.local.TrainingDatabase
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi

class TrainingViewModel(application: Application) : AndroidViewModel(application) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val trainingDatabase: TrainingDatabase
        get() = TrainingDatabase.getInstance(getApplication())

    private val repoTraining: RepoTraining
        get() = RepoTrainingImp(trainingDatabase.trainingDao)

    private val observeTrainingsInteractor: ObserveTrainingsInteractor
        get() = ObserveTrainingsInteractor(repoTraining)

    private val insertTrainingInteractor: InsertTrainingInteractor
        get() = InsertTrainingInteractor(repoTraining)

    private val observeLastFifeTrainingsInteractor: ObserveLastFifeTrainingsInteractor
        get() = ObserveLastFifeTrainingsInteractor(
            observeTrainingsInteractor,
            insertTrainingInteractor
        )

    private val observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor
        get() = ObserveCurrentTrainingWithExercisesInteractor(observeLastFifeTrainingsInteractor)

    private val _trainingWithExercises: MutableLiveData<TrainingWithExercisesUi> = MutableLiveData()

    val trainingWithExercises: LiveData<TrainingWithExercisesUi>
        get() = _trainingWithExercises

    init {
        getCurrentTrainingWithExercises()
    }

    private fun getCurrentTrainingWithExercises() {
        observeCurrentTrainingWithExercisesInteractor()
            .map(::TrainingWithExercisesUi)
            .subscribe(_trainingWithExercises::postValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }

}