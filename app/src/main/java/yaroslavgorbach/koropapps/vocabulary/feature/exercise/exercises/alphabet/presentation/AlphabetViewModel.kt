package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticValueInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseWordCategory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AlphabetViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val insertStatisticValueInteractor: InsertStatisticValueInteractor,
    private val insertStatisticTimeInteractor: InsertStatisticTimeInteractor,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

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

    var passedLettersCount: Int = 0
        private set

    private val timeIntervals: MutableList<Long> = arrayListOf()

    private var previousTime: Long = Date().time

    val averageTimeOnWord: Float
        get() = (timeIntervals.sum() / timeIntervals.size) / 1000f

    init {
        refreshLetter()
        refreshProgressTimer()
    }

    fun onNextLetterClick() {
        refreshLetter()
        incrementPassedLettersCount()
        refreshProgressTimer()
        measureClickInterval()
    }

    fun onTimerFinished() {
        incrementExercisePerformed()
        scope.cancel()
        _progress.value = 0
    }

   private fun measureClickInterval() {
        val currentTime = Date().time
        val interval = currentTime - previousTime
        previousTime = currentTime
        timeIntervals.add(interval)
    }

    private fun refreshLetter() {
        _letter.value = letters.value?.firstOrNull()
            .also { letter -> letters.value = letters.value?.filter { it != letter } }
    }

    private fun incrementPassedLettersCount() {
        passedLettersCount++
    }

    private fun incrementExercisePerformed() {
        if (exerciseType is ExerciseType.Training) {
            incrementExercisePerformedInteractor(exerciseType.exerciseId)
                .subscribe()
                .let(disposables::add)
        }
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

    private fun saveStatistics(doOnComplete: () -> Unit) {
        insertStatisticValueInteractor.invoke(
            StatisticsEntityFactory().createValueEntity(
                exerciseType.getExerciseName(),
                passedLettersCount
            )
        ).andThen(
            insertStatisticTimeInteractor.invoke(
                StatisticsEntityFactory().createTimeEntity(
                    exerciseType.getExerciseName(),
                    averageTimeOnWord
                )
            )
        )
            .doOnComplete(doOnComplete)
            .subscribe()
            .let(disposables::add)
    }

    private fun disposeDisposables() {
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveStatistics {
            disposeDisposables()
        }
    }
}