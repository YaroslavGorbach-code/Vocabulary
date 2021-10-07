package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation

import androidx.lifecycle.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveLastFifeTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseCategoryFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import java.util.Locale.filter
import javax.inject.Inject

class ExercisesListViewModel @Inject constructor(
    private val observeLastFifeTrainingsInteractor: ObserveLastFifeTrainingsInteractor,
    private val getExercisesInteractor: GetExercisesInteractor,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _exercises: MutableLiveData<List<ExerciseUi>> = MutableLiveData()

    val exercises: LiveData<List<ExerciseUi>>
        get() = _exercises

    private val _training: MutableLiveData<TrainingUi> = MutableLiveData()

    val training: LiveData<TrainingUi>
        get() = _training

    private val _exercisesFilterUi: MutableLiveData<ExerciseCategoryFilterUi> =
        MutableLiveData()

    val exercisesFilterUi: LiveData<ExerciseCategoryFilterUi>
        get() = _exercisesFilterUi

    init {
        getAndFilterExercises(ExerciseCategoryFilterUi.ALL)
        getLastFifeTrainings()
    }

    private fun getLastFifeTrainings() {
        observeLastFifeTrainingsInteractor()
            .map(::TrainingUi)
            .subscribe(_training::postValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }

    fun changeExercisesFilter(filterUi: ExerciseCategoryFilterUi) {
        getAndFilterExercises(filterUi)
    }

    private fun getAndFilterExercises(filterUi: ExerciseCategoryFilterUi) {
        viewModelScope.launch {

            getExercisesInteractor()
                .map { list -> list.map(::ExerciseUi) }
                .map { exercisesUi ->
                    when (filterUi) {
                        ExerciseCategoryFilterUi.ALL -> {
                            exercisesUi
                        }
                        ExerciseCategoryFilterUi.VOCABULARY -> exercisesUi.filter {
                            it.category == ExerciseCategory.VOCABULARY
                        }
                        ExerciseCategoryFilterUi.COMMUNICATION -> exercisesUi.filter {
                            it.category == ExerciseCategory.COMMUNICATION
                        }
                        ExerciseCategoryFilterUi.FAVORITE -> {
                            exercisesUi.filter { it.isFavorite }
                        }
                    }
                }.collect { exercises ->
                    _exercises.postValue(exercises)
                    _exercisesFilterUi.value = filterUi
                }
        }
    }
}