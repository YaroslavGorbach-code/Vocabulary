package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetStatisticsLevelInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi
import javax.inject.Inject

class LevelViewModel @Inject constructor(
    private val getStatisticsLevelInteractor: GetStatisticsLevelInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _levelInfoUi: MutableLiveData<LevelInfoUi> = MutableLiveData()

    val levelInfoUi: LiveData<LevelInfoUi>
        get() = _levelInfoUi

    init {
        getLevel()
    }

    private fun getLevel() {
        getStatisticsLevelInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map(::LevelInfoUi)
            .subscribe(_levelInfoUi::setValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}