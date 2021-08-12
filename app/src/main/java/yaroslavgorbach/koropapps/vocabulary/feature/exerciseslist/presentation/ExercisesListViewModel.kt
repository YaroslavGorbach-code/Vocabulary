package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesInteract
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp
import yaroslavgorbach.koropapps.vocabulary.data.training.local.TrainingDatabase
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation.factory.TrainingFactory

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

    private val getExercisesInteractor: GetExercisesInteract
        get() = GetExercisesInteract(exercisesRepo)

    private val _exercises: MutableLiveData<List<ExerciseUi>> = MutableLiveData()

    val exercises: LiveData<List<ExerciseUi>>
        get() = _exercises

    private val _training: MutableLiveData<TrainingUi> = MutableLiveData()

    val training: LiveData<TrainingUi>
        get() = _training

    init {
        getExercises()
        getTraining()
    }

    private fun getTraining() {
        observeTrainingsInteractor()
            .doOnNext { list ->
                while (list.size < 5) {
                    list.toMutableList().add(TrainingFactory().create())
                }
            }
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