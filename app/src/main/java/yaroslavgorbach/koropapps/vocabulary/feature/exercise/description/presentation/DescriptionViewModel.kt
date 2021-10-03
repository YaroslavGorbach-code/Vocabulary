package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsValueInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartTimeUi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartValueUi
import java.util.*
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val observeStatisticsValueInteractor: ObserveStatisticsValueInteractor,
    private val observeStatisticsTimeInteractor: ObserveStatisticsTimeInteractor,
    private val exerciseName: ExerciseName
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    val descriptionRes: Int
        get() = ExerciseNameToDescriptionResMapper().map(exerciseName)

    val exerciseIconRes: Int
        get() = ExerciseNameToIconResMapper().map(exerciseName)

    private val _chartValueUi: MutableLiveData<ChartValueUi> = MutableLiveData()

    val chartValueUi: LiveData<ChartValueUi>
        get() = _chartValueUi

    private val _chartTimeUi: MutableLiveData<ChartTimeUi> = MutableLiveData()

    val chartTimeUi: LiveData<ChartTimeUi>
        get() = _chartTimeUi

    init {
        observeValueStatistics()
        observeTimeStatistics()
    }

    private fun observeValueStatistics() {
        observeStatisticsValueInteractor(exerciseName.id)
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.takeLast(ChartValueUi.MAX_ITEMS_COUNT) }
            .map { ChartValueUi(it, exerciseName) }
            .subscribe(_chartValueUi::setValue)
            .let(disposables::add)
    }

    private fun observeTimeStatistics() {
        observeStatisticsTimeInteractor(exerciseName.id)
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.takeLast(ChartTimeUi.MAX_ITEMS_COUNT) }
            .map { ChartTimeUi(it, exerciseName) }
            .subscribe(_chartTimeUi::setValue)
            .let(disposables::add)
    }

    fun onNextChartValue() {
        observeStatisticsValueInteractor(exerciseName.id)
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter {
                    it.date > _chartValueUi.value?.dates?.last() ?: Date()
                }
            }
            .map { it.take(ChartValueUi.MAX_ITEMS_COUNT) }
            .map { ChartValueUi(it, exerciseName) }
            .map { chartUi ->
                if (chartUi.labels.isEmpty().not()) {
                    _chartValueUi.value = chartUi
                }
            }
            .subscribe()
            .let(disposables::add)
    }

    fun onPreviousChartValue() {
        observeStatisticsValueInteractor(exerciseName.id)
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter {
                    it.date < _chartValueUi.value?.dates?.first() ?: Date()
                }
            }
            .map { it.takeLast(ChartValueUi.MAX_ITEMS_COUNT) }
            .map{ ChartValueUi(it, exerciseName) }
            .map { chartUi ->
                if (chartUi.labels.isEmpty().not()) {
                    _chartValueUi.value = chartUi
                }
            }
            .subscribe()
            .let(disposables::add)
    }

    fun onNextChartTime() {
        observeStatisticsTimeInteractor(exerciseName.id)
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter {
                    it.date > _chartTimeUi.value?.dates?.last() ?: Date()
                }
            }
            .map { it.take(ChartTimeUi.MAX_ITEMS_COUNT) }
            .map { ChartTimeUi(it, exerciseName) }
            .map { chartUi ->
                if (chartUi.labels.isEmpty().not()) {
                    _chartTimeUi.value = chartUi
                }
            }
            .subscribe()
            .let(disposables::add)
    }

    fun onPreviousChartTime() {
        observeStatisticsTimeInteractor(exerciseName.id)
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter {
                    it.date < _chartTimeUi.value?.dates?.first() ?: Date()
                }
            }
            .map { it.takeLast(ChartTimeUi.MAX_ITEMS_COUNT) }
            .map { ChartTimeUi(it, exerciseName) }
            .map { chartUi ->
                if (chartUi.labels.isEmpty().not()) {
                    _chartTimeUi.value = chartUi
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