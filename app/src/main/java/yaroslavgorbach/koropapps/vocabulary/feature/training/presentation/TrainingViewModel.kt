package yaroslavgorbach.koropapps.vocabulary.feature.training.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveCurrentTrainingWithExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import javax.inject.Inject


class TrainingViewModel @Inject constructor(
    private val observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _trainingWithExercises: MutableLiveData<TrainingWithExercisesUi> = MutableLiveData()

    val trainingWithExercises: LiveData<TrainingWithExercisesUi>
        get() = _trainingWithExercises

    fun getCurrentTrainingWithExercises() {
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