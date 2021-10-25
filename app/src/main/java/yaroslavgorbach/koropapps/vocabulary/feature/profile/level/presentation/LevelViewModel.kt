package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation

import androidx.lifecycle.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ObserveAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetStatisticsCommonInfoInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.OratorLevelInfoUi
import javax.inject.Inject

class LevelViewModel @Inject constructor(
    private val getStatisticsCommonInfoInteractor: GetStatisticsCommonInfoInteractor,
    private val achievementInteractor: AchieveAchievementInteractor,
    private val observeAchievementsInteractor: ObserveAchievementsInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _oratorLevelInfo: MutableLiveData<OratorLevelInfoUi> = MutableLiveData()

    val oratorLevelInfoUi: LiveData<OratorLevelInfoUi>
        get() = _oratorLevelInfo

    val achievements: LiveData<List<Achievement>> = observeAchievementsInteractor().asLiveData()

    init {
        getCommonStatisticInfo()
    }

    private fun getCommonStatisticInfo() {
        getStatisticsCommonInfoInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.dailyTrainingsCompleted > 0) {
                    viewModelScope.launch { achievementInteractor(AchievementName.FIRST_DAILY_TRAINING_COMPLETE) }
                }
            }
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