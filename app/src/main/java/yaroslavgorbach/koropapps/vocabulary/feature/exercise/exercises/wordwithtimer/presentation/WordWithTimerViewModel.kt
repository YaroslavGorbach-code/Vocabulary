package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseWordMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.MutableLiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.TimerImp
import yaroslavgorbach.koropapps.vocabulary.utils.send
import javax.inject.Inject

class WordWithTimerViewModel @Inject constructor(
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
            ExerciseNameToExerciseWordMapper().map(exerciseType.getExerciseName()).resArray
        ).toList()
    )

    private val _letter: MutableLiveData<String> = MutableLiveData()

    val letter: LiveData<String>
        get() = _letter

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    val timerState: LiveData<Timer.State>
        get() = timer.state

    private val _finisExerciseEvent: MutableLiveEvent<Unit> = MutableLiveEvent()

    val finisExerciseEvent: LiveEvent<Unit>
        get() = _finisExerciseEvent

    private val timer: Timer = TimerImp()

    init {
        startOrRefreshProgressTimer()
        refreshWord()
    }

    override fun onNextClick() {
        super.onNextClick()
        startOrRefreshProgressTimer()
        refreshWord()
    }

    fun onTimerFinished() {
        incrementExercisePerformed()
        timer.cancel()
    }

    private fun refreshWord() {
        when (exerciseType.getExerciseName()) {
            ExerciseName.ALPHABET_ADJECTIVES, ExerciseName.ALPHABET_NOUN, ExerciseName.ALPHABET_VERBS -> {
                val letter: String? = letters.value?.firstOrNull()

                if (letter != null) {
                    _letter.value = letter
                    letters.value = letters.value?.filter { it != letter }
                } else {
                    _finisExerciseEvent.send(Unit)
                }
            }

            ExerciseName.DICTIONARY_ADJECTIVES, ExerciseName.DICTIONARY_NOUN, ExerciseName.DICTIONARY_VERBS -> {
                _letter.value = numberOnNextCLicked.toString()
            }
            else -> {
            }
        }
    }

    private fun startOrRefreshProgressTimer() {
        when (exerciseType.getExerciseName()) {
            ExerciseName.ALPHABET_ADJECTIVES, ExerciseName.ALPHABET_NOUN, ExerciseName.ALPHABET_VERBS -> {
                timer.cancel()
                timer.start(Timer.ONE_SECOND * 5, 1)
            }

            ExerciseName.DICTIONARY_ADJECTIVES, ExerciseName.DICTIONARY_NOUN, ExerciseName.DICTIONARY_VERBS -> {
                if (timer.state.value == null) {
                    timer.start(Timer.ONE_MINUTE, 1)
                }
            }
            else -> {
            }
        }

    }
}