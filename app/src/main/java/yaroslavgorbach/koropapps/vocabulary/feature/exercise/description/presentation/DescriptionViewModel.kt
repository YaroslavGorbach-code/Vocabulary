package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ChangeExerciseFavoriteInteractor
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ObserveExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsValueInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartTimeUi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartValueUi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.DescriptionState
import java.util.*
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val observeStatisticsValueInteractor: ObserveStatisticsValueInteractor,
    private val observeStatisticsTimeInteractor: ObserveStatisticsTimeInteractor,
    private val changeExerciseFavoriteInteractor: ChangeExerciseFavoriteInteractor,
    private val observeExerciseInteractor: ObserveExerciseInteractor,
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

    private val _isExerciseFavorite: MutableLiveData<Boolean> = MutableLiveData()

    val isExerciseFavorite: LiveData<Boolean>
        get() = _isExerciseFavorite

    private val _descriptionState: MutableLiveData<DescriptionState> = MutableLiveData(DescriptionState.COLLAPSED)

    val descriptionState: LiveData<DescriptionState>
        get() = _descriptionState

    init {
        observeValueStatistics()
        observeTimeStatistics()
        observeIsExerciseFavorite()
    }

    private fun observeValueStatistics() {
        observeStatisticsValueInteractor(exerciseName)
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.takeLast(ChartValueUi.MAX_ITEMS_COUNT) }
            .map { ChartValueUi(it, exerciseName) }
            .subscribe(_chartValueUi::setValue)
            .let(disposables::add)
    }

    private fun observeTimeStatistics() {
        observeStatisticsTimeInteractor(exerciseName)
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.takeLast(ChartTimeUi.MAX_ITEMS_COUNT) }
            .map { ChartTimeUi(it, exerciseName) }
            .subscribe(_chartTimeUi::setValue)
            .let(disposables::add)
    }

    private fun observeIsExerciseFavorite() {
        viewModelScope.launch {
            observeExerciseInteractor(exerciseName).map { it.isFavorite }
                .collect(_isExerciseFavorite::postValue)
        }
    }

    fun onNextChartValue() {
        observeStatisticsValueInteractor(exerciseName)
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
        observeStatisticsValueInteractor(exerciseName)
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter {
                    it.date < _chartValueUi.value?.dates?.first() ?: Date()
                }
            }
            .map { it.takeLast(ChartValueUi.MAX_ITEMS_COUNT) }
            .map { ChartValueUi(it, exerciseName) }
            .map { chartUi ->
                if (chartUi.labels.isEmpty().not()) {
                    _chartValueUi.value = chartUi
                }
            }
            .subscribe()
            .let(disposables::add)
    }

    fun onNextChartTime() {
        observeStatisticsTimeInteractor(exerciseName)
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
        observeStatisticsTimeInteractor(exerciseName)
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

    fun changeExerciseFavorite() {
        viewModelScope.launch {
            changeExerciseFavoriteInteractor(exerciseName)
        }
    }

    fun changeDescriptionState() {
        when(descriptionState.value){
            DescriptionState.COLLAPSED -> _descriptionState.value = DescriptionState.OPENED
            DescriptionState.OPENED -> _descriptionState.value = DescriptionState.COLLAPSED
            null -> {}
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }

}