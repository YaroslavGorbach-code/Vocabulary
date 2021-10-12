package yaroslavgorbach.koropapps.vocabulary.feature.training.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveCurrentTrainingWithExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import javax.inject.Inject
import io.reactivex.rxjava3.observables.GroupedObservable
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseCategoryFilterUi


class TrainingViewModel @Inject constructor(
    private val observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _trainingWithExercises: MutableLiveData<TrainingWithExercisesUi> = MutableLiveData()

    val trainingWithExercises: LiveData<TrainingWithExercisesUi>
        get() = _trainingWithExercises

    private var currentViewPage = 0

    private var currentExerciseCategoryFilter = TrainingExerciseCategoryFilterUi.ALL

    fun getCurrentTrainingWithExercises() {
        observeCurrentTrainingWithExercisesInteractor()
            .map(::TrainingWithExercisesUi)
            .map { training -> training.apply { currentViewPagerPage = currentViewPage } }
            .map { training -> training.apply { currentFilter = currentExerciseCategoryFilter } }
            .subscribe(_trainingWithExercises::postValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }

    fun setCurrentPage(currentPage: Int) {
        currentViewPage = currentPage
    }

    fun changeFilter(filterUi: TrainingExerciseCategoryFilterUi) {
        currentExerciseCategoryFilter = filterUi
    }

}