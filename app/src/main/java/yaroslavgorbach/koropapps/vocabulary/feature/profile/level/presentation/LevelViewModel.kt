package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ObserveAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetStatisticsCommonInfoInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.OratorLevelInfoUi
import javax.inject.Inject

class LevelViewModel @Inject constructor(
    private val getStatisticsCommonInfoInteractor: GetStatisticsCommonInfoInteractor,
    private val observeAchievementsInteractor: ObserveAchievementsInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _oratorLevelInfo: MutableLiveData<OratorLevelInfoUi> = MutableLiveData()

    val oratorLevelInfoUi: LiveData<OratorLevelInfoUi>
        get() = _oratorLevelInfo

    val achievements: LiveData<List<Achievement>>
        get() = observeAchievementsInteractor().asLiveData()

    init {
        getCommonStatisticInfo()
    }

    private fun getCommonStatisticInfo() {
        getStatisticsCommonInfoInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map(::OratorLevelInfoUi)
            .subscribe(_oratorLevelInfo::setValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}