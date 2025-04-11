package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ChangeExerciseFavoriteInteractor
import yaroslavgorbach.koropapps.vocabulary.business.exercises.ObserveExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsValueInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.DescriptionState
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.StatisticItemUi
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManager
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val observeStatisticsValueInteractor: ObserveStatisticsValueInteractor,
    private val observeStatisticsTimeInteractor: ObserveStatisticsTimeInteractor,
    private val changeExerciseFavoriteInteractor: ChangeExerciseFavoriteInteractor,
    private val observeExerciseInteractor: ObserveExerciseInteractor,
    private val exerciseName: ExerciseName,
    private val addManager: AdManager
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    val descriptionRes: Int
        get() = ExerciseNameToDescriptionResMapper().map(exerciseName)

    val exerciseIconRes: Int
        get() = ExerciseNameToIconResMapper().map(exerciseName)

    private val _statisticItems: MutableLiveData<List<StatisticItemUi>> = MutableLiveData()

    val statisticItems: LiveData<List<StatisticItemUi>>
        get() = _statisticItems

    private val _chosenStatisticItem: MutableLiveData<StatisticItemUi?> = MutableLiveData()

    val chosenStatisticItem: LiveData<StatisticItemUi?>
        get() = _chosenStatisticItem

    private val _isExerciseFavorite: MutableLiveData<Boolean> = MutableLiveData()

    val isExerciseFavorite: LiveData<Boolean>
        get() = _isExerciseFavorite

    private val _descriptionState: MutableLiveData<DescriptionState> =
        MutableLiveData(DescriptionState.COLLAPSED)

    val descriptionState: LiveData<DescriptionState>
        get() = _descriptionState

    init {
        observeIsExerciseFavorite()
        observeStatistic()
    }

    fun showAd(activity: Activity){
        viewModelScope.launch {
            addManager.showInterstitial(activity)
        }
    }

    private fun observeStatistic() {
        observeStatisticsTimeInteractor(exerciseName)
            .zipWith(observeStatisticsValueInteractor(exerciseName)) { timeList, valueList ->
                timeList.zip(valueList, ::StatisticItemUi)
            }
            .map { statistics ->
                statistics.mapIndexed { index, statisticItemUi ->
                    statisticItemUi.copy(isChosen = index == statistics.size - 1)
                }
            }
            .doOnNext { statistics -> _chosenStatisticItem.postValue(statistics.firstOrNull { it.isChosen }) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(_statisticItems::postValue)
            .let(disposables::add)
    }

    private fun observeIsExerciseFavorite() {
        viewModelScope.launch {
            observeExerciseInteractor(exerciseName).map { it.isFavorite }
                .collect(_isExerciseFavorite::postValue)
        }
    }

    fun changeExerciseFavorite() {
        viewModelScope.launch {
            changeExerciseFavoriteInteractor(exerciseName)
        }
    }

    fun changeDescriptionState() {
        when (descriptionState.value) {
            DescriptionState.COLLAPSED -> _descriptionState.value = DescriptionState.OPENED
            DescriptionState.OPENED -> _descriptionState.value = DescriptionState.COLLAPSED
            null -> {
            }
        }
    }

    fun choseStatisticItem(statisticItemUi: StatisticItemUi) {
        _chosenStatisticItem.value = statisticItemUi
        _statisticItems.value = _statisticItems.value?.toMutableList()?.apply {
            map { it.isChosen = false }
            val index = indexOf(statisticItemUi)
            val item = get(index)
            set(index, item.copy(isChosen = true))
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}