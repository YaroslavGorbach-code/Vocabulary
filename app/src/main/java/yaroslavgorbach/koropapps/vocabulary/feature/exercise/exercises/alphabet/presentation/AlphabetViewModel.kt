package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseWordCategory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer.Companion.ONE_SECOND
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.TimerImp
import javax.inject.Inject

class AlphabetViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    saveStatisticsInteractor: SaveStatisticsInteractor,
    observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor
) : BaseExerciseViewModel(
    exerciseType,
    incrementExercisePerformedInteractor,
    saveStatisticsInteractor,
    observeTrainingExerciseInteractor
) {

    private val letters: MutableLiveData<List<String>> = MutableLiveData(
        application.applicationContext.resources.getStringArray(
            ExerciseWordCategory.LETTERS.resId
        ).toList()
    )

    private val _letter: MutableLiveData<String> = MutableLiveData()

    val letter: LiveData<String>
        get() = _letter

    val timerState: LiveData<Timer.State>
        get() = timer.state

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val timer: Timer = TimerImp()

    init {
        refreshLetter()
        startOrRefreshProgressTimer()
    }

    override fun onNextClick() {
        super.onNextClick()
        startOrRefreshProgressTimer()
        refreshLetter()
    }

    fun onTimerFinished() {
        incrementExercisePerformed()
        saveStatistics()
        timer.cancel()
    }

    private fun refreshLetter() {
        _letter.value = letters.value?.firstOrNull()
            .also { letter -> letters.value = letters.value?.filter { it != letter } }
    }

    private fun startOrRefreshProgressTimer() {
        timer.cancel()
        timer.start(ONE_SECOND * 5, 1)
    }
}