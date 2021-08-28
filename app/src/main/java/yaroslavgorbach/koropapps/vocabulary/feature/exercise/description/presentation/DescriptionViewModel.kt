package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.description.GetDescriptionInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartUi
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val getDescriptionInteractor: GetDescriptionInteractor,
    private val observeStatisticsInteractor: ObserveStatisticsInteractor,
    private val exerciseName: ExerciseName
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _description: MutableLiveData<Description> = MutableLiveData()
        get() {
            getDescriptionInteractor(exerciseName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(field::setValue)
                .let(disposables::add)
            return field
        }

    val description: LiveData<Description>
        get() = _description

    private val _chartUi: MutableLiveData<ChartUi> = MutableLiveData()

    val chartUi: LiveData<ChartUi>
        get() = _chartUi

    init {
        observeStatistics()
    }

    private fun observeStatistics() {
        observeStatisticsInteractor(exerciseName.id)
            .observeOn(AndroidSchedulers.mainThread())
            .map(::ChartUi)
            .subscribe(_chartUi::setValue)
    }

    fun onNextChart() {

    }

    fun onPreviousChart() {

    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}