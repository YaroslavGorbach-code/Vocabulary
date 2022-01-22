package yaroslavgorbach.koropapps.vocabulary.feature.training.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveCurrentTrainingWithExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObservePreviousTrainingInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import javax.inject.Inject


class TrainingViewModel @Inject constructor(
    private val observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor,
    private val observePreviousTrainingInteractor: ObservePreviousTrainingInteractor,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _trainingWithExercises: MutableLiveData<TrainingWithExercisesUi> = MutableLiveData()

    val trainingWithExercises: LiveData<TrainingWithExercisesUi>
        get() = _trainingWithExercises

    fun getCurrentTrainingWithExercises() {
        Observable.zip(
            observeCurrentTrainingWithExercisesInteractor(),
            observePreviousTrainingInteractor(),
        ) { current, previous ->
            TrainingWithExercisesUi(
                trainingWithExercisesEntity = current,
                previousTrainingWithExercisesEntity = previous
            )
        }.subscribe(_trainingWithExercises::postValue) {
            Log.i("dsxasdc", it.message.toString())
        }.let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}