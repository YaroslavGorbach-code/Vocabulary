package yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticDaysInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartValueUi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.model.ChartDayUi
import java.util.*
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val observeStatisticDaysInteractor: ObserveStatisticDaysInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _chartDayUi: MutableLiveData<ChartDayUi> = MutableLiveData()

    val chartDayUi: LiveData<ChartDayUi>
        get() = _chartDayUi

    init {
        observeDaysStatistics()
    }

    private fun observeDaysStatistics() {
        observeStatisticDaysInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.takeLast(ChartDayUi.MAX_ITEMS_COUNT) }
            .map(::ChartDayUi)
            .subscribe(_chartDayUi::setValue)
            .let(disposables::add)
    }

    fun onNextChart() {
        observeStatisticDaysInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter {
                    it.date > _chartDayUi.value?.dates?.last() ?: Date()
                }
            }
            .map { it.take(ChartValueUi.MAX_ITEMS_COUNT) }
            .map(::ChartDayUi)
            .map { chartUi ->
                if (chartUi.labels.isEmpty().not()) {
                    _chartDayUi.value = chartUi
                }
            }
            .subscribe()
            .let(disposables::add)
    }

    fun onPreviousChart() {
        observeStatisticDaysInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter {
                    it.date < _chartDayUi.value?.dates?.first() ?: Date()
                }
            }
            .map { it.takeLast(ChartValueUi.MAX_ITEMS_COUNT) }
            .map(::ChartDayUi)
            .map { chartUi ->
                if (chartUi.labels.isEmpty().not()) {
                    _chartDayUi.value = chartUi
                }
            }
            .subscribe()
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}