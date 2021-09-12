package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseWordCategory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
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

    private val scope
        get() = CoroutineScope(viewModelScope.coroutineContext)

    private val _letter: MutableLiveData<String> = MutableLiveData()

    val letter: LiveData<String>
        get() = _letter

    private val _progress = MutableLiveData<Int>()

    val progress: LiveData<Int>
        get() = _progress

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    init {
        refreshLetter()
        refreshProgressTimer()
    }

    override fun onNextClick() {
        super.onNextClick()
        refreshProgressTimer()
        refreshLetter()
    }

    fun onTimerFinished() {
        incrementExercisePerformed()
        scope.cancel()
        _progress.value = 0
        saveStatistics()
    }

    private fun refreshLetter() {
        _letter.value = letters.value?.firstOrNull()
            .also { letter -> letters.value = letters.value?.filter { it != letter } }
    }

    private fun refreshProgressTimer() {
        scope.coroutineContext.cancelChildren()
        scope.launch {
            (0..100).forEach {
                delay(50)
                _progress.value = it
            }
        }
    }

}