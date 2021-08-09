package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.repo.RepoExercisesImp
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi

class ExercisesListViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val exercisesRepo: RepoExercises
        get() = RepoExercisesImp()

    private val getExercisesUseCase: GetExercisesUseCase
        get() = GetExercisesUseCase(exercisesRepo)

    private val _exercises: MutableLiveData<List<ExerciseUi>> = MutableLiveData()

    val exercises: LiveData<List<ExerciseUi>>
        get() = _exercises

    init {
        getExercises()
    }

    private fun getExercises() {
        getExercisesUseCase()
            .map { list -> list.map(::ExerciseUi) }
            .subscribe(Consumer(_exercises::setValue))
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}