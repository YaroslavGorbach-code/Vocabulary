package yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.phrase.ObserveTodayPhraseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ChangeAdFeatureAvailability
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveAdFeatureAvailability
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetStatisticsCommonInfoInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticDaysInteractor
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.ChartValueUi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.model.ChartDayUi
import java.util.*
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val observeStatisticDaysInteractor: ObserveStatisticDaysInteractor,
    private val getStatisticsCommonInfoInteractor: GetStatisticsCommonInfoInteractor,
    private val observeTodayPhraseInteractor: ObserveTodayPhraseInteractor,
    private val changeAdFeatureAvailability: ChangeAdFeatureAvailability,
    private val observeAdsFeatureAvailability: ObserveAdFeatureAvailability
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _chartDayUi: MutableLiveData<ChartDayUi> = MutableLiveData()

    val chartDayUi: LiveData<ChartDayUi>
        get() = _chartDayUi

    private val _levelInfoUi: MutableLiveData<LevelInfoUi> = MutableLiveData()

    val levelInfoUi: LiveData<LevelInfoUi>
        get() = _levelInfoUi

    init {
        observeDaysStatistics()
        getLevel()
    }

    suspend fun observePhrase(): LiveData<Phrase> {
        return observeTodayPhraseInteractor().asLiveData()
    }

    suspend fun observeAdsAvailability(): LiveData<Boolean> {
        return observeAdsFeatureAvailability().asLiveData()
    }

    private fun getLevel() {
        getStatisticsCommonInfoInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map(::LevelInfoUi)
            .subscribe(_levelInfoUi::setValue)
            .let(disposables::add)
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

    fun onChangeAdsAvailable(isAvailable: Boolean) {
        viewModelScope.launch {
            changeAdFeatureAvailability.invoke(isAvailable)
        }
    }
}