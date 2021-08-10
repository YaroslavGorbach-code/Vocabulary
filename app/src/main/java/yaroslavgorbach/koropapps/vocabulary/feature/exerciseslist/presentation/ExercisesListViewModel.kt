package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesIntearactor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi

class ExercisesListViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val exercisesRepo: RepoExercises
        get() = RepoExercisesImp()

    private val repoTraining: RepoTraining
        get() = RepoTrainingImp()

    private val observeTrainingsInteractor: ObserveTrainingsInteractor
        get() = ObserveTrainingsInteractor(repoTraining)

    private val getExercisesInteractor: GetExercisesIntearactor
        get() = GetExercisesIntearactor(exercisesRepo)

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
            .map { it.take(5) }
            .map(::TrainingUi)
            .subscribe(_training::postValue)
            .let(disposables::add)

    }

    private fun getExercises() {
        getExercisesInteractor()
            .map { list -> list.map(::ExerciseUi) }
            .subscribe(Consumer(_exercises::postValue))
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}