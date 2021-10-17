package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveLastFifeTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseCategoryFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExercisesWithFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import javax.inject.Inject

class ExercisesListViewModel @Inject constructor(
    private val observeLastFifeTrainingsInteractor: ObserveLastFifeTrainingsInteractor,
    private val getExercisesInteractor: GetExercisesInteractor,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _exercisesWithFilter: MutableLiveData<ExercisesWithFilterUi> = MutableLiveData()

    val exercisesWithFilter: LiveData<ExercisesWithFilterUi>
        get() = _exercisesWithFilter

    private val _training: MutableLiveData<TrainingUi> = MutableLiveData()

    val training: LiveData<TrainingUi>
        get() = _training

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
                        ExerciseCategoryFilterUi.ALL -> exercisesUi

                        ExerciseCategoryFilterUi.VOCABULARY -> exercisesUi.filter {
                            it.category == ExerciseCategory.VOCABULARY
                        }

                        ExerciseCategoryFilterUi.COMMUNICATION -> exercisesUi.filter {
                            it.category == ExerciseCategory.COMMUNICATION
                        }

                        ExerciseCategoryFilterUi.DICTION_AND_ARTICULATION -> exercisesUi.filter {
                            it.category == ExerciseCategory.DICTION_AND_ARTICULATION
                        }

                        ExerciseCategoryFilterUi.FAVORITE -> exercisesUi.filter { it.isFavorite }

                    }
                }
                .map { ExercisesWithFilterUi(it, filterUi) }
                .collect(_exercisesWithFilter::postValue)
        }
    }
}