package yaroslavgorbach.koropapps.vocabulary.feature.training.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveCurrentTrainingWithExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import javax.inject.Inject
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseCategoryFilterUi


class TrainingViewModel @Inject constructor(
    private val observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor,
    private val achieveAchievementInteractor: AchieveAchievementInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _trainingWithExercises: MutableLiveData<TrainingWithExercisesUi> = MutableLiveData()

    val trainingWithExercises: LiveData<TrainingWithExercisesUi>
        get() = _trainingWithExercises

    private var currentViewPage = 0

    private var currentExerciseCategoryFilter = TrainingExerciseCategoryFilterUi.ALL

    private fun achieveFirstDailyTrainingComplete(training: TrainingWithExercisesUi) {
        viewModelScope.launch {
            if (training.isTrainingFinished) {
                achieveAchievementInteractor(AchievementName.FIRST_DAILY_TRAINING_COMPLETE)
            }
        }
    }

    fun getCurrentTrainingWithExercises() {
        observeCurrentTrainingWithExercisesInteractor()
            .map(::TrainingWithExercisesUi)
            .map { training -> training.apply { currentViewPagerPage = currentViewPage } }
            .map { training -> training.apply { currentFilter = currentExerciseCategoryFilter } }
            .doOnNext(this::achieveFirstDailyTrainingComplete)
            .subscribe(_trainingWithExercises::postValue)
            .let(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }

    fun setCurrentPage(currentPage: Int) {
        currentViewPage = currentPage
    }

    fun changeFilter(filterUi: TrainingExerciseCategoryFilterUi) {
        currentExerciseCategoryFilter = filterUi
        getCurrentTrainingWithExercises()
    }

}