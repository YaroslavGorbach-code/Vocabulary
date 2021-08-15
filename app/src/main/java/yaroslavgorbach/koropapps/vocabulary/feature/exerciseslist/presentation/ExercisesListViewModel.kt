package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.InsertTrainingInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveLastFifeTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp
import yaroslavgorbach.koropapps.vocabulary.data.training.local.TrainingDatabase
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi

class ExercisesListViewModel(application: Application) : AndroidViewModel(application) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val trainingDatabase: TrainingDatabase
        get() = TrainingDatabase.getInstance(getApplication())

    private val exercisesRepo: RepoExercises
        get() = RepoExercisesImp()

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

    private val getExercisesInteractor: GetExercisesInteractor
        get() = GetExercisesInteractor(exercisesRepo)

    private val _exercises: MutableLiveData<List<ExerciseUi>> = MutableLiveData()

    val exercises: LiveData<List<ExerciseUi>>
        get() = _exercises

    private val _training: MutableLiveData<TrainingUi> = MutableLiveData()

    val training: LiveData<TrainingUi>
        get() = _training

    init {
        getExercises()
        getLastFifeTrainings()
    }

    private fun getLastFifeTrainings() {
        observeLastFifeTrainingsInteractor()
            .map(::TrainingUi)
            .subscribe(_training::postValue)
            .let(disposables::add)

    }

    private fun getExercises() {
        getExercisesInteractor()
            .map { list -> list.map(::ExerciseUi) }
            .subscribe(_exercises::postValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}