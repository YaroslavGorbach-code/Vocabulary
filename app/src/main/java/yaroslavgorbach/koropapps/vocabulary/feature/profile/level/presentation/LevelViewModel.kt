package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetStatisticsLevelInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.OratorLevelInfoUi
import javax.inject.Inject

class LevelViewModel @Inject constructor(
    private val getStatisticsLevelInteractor: GetStatisticsLevelInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _Orator_levelInfoUi: MutableLiveData<OratorLevelInfoUi> = MutableLiveData()

    val oratorLevelInfoUi: LiveData<OratorLevelInfoUi>
        get() = _Orator_levelInfoUi

    init {
        getLevel()
    }

    private fun getLevel() {
        getStatisticsLevelInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map(::OratorLevelInfoUi)
            .subscribe(_Orator_levelInfoUi::setValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}