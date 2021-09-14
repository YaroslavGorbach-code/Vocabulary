package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.dictionary.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.TimerImp
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(
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

    val timerState: LiveData<Timer.State>
        get() = timer.state

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val _numberOfWords: MutableLiveData<Int> = MutableLiveData()

    val numberOfWords: LiveData<Int>
        get() {
            return _numberOfWords
        }

    private val timer: Timer = TimerImp()

    init {
        startExerciseTimer()
    }

    override fun onNextClick() {
        super.onNextClick()
        _numberOfWords.value = numberOnNextCLicked
    }

    fun onTimerFinished() {
        incrementExercisePerformed()
    }

    private fun startExerciseTimer() {
        timer.start(Timer.ONE_MINUTE, 1)
    }
}