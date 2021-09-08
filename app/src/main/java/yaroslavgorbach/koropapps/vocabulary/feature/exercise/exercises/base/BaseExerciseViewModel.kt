package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import java.util.*

open class BaseExerciseViewModel(
    private val exerciseType: ExerciseType,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val saveStatisticsInteractor: SaveStatisticsInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor
) : ViewModel() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    var numberOnNextCLicked: Int = 0
        private set

    private val timeIntervals: MutableList<Long> = arrayListOf()

    private var previousTime: Long = Date().time

    private val summaryTimeSpendOnExercise: Long
        get() = timeIntervals.sum()

    val averageTimeOnWord: Float
        get() {
            return try {
                (summaryTimeSpendOnExercise / timeIntervals.size) / 1000f
            } catch (ex: Exception) {
                0f
            }
        }

    private val _exercise: MutableLiveData<TrainingExerciseUi> = MutableLiveData()
        get() {
            if (exerciseType is ExerciseType.Training) {
                observeTrainingExerciseInteractor(exerciseType.exerciseId)
                    .map(::TrainingExerciseUi)
                    .subscribe(field::postValue)
                    .let(disposables::add)
            }
            return field
        }

    val exercise: LiveData<TrainingExerciseUi>
        get() = _exercise

    open fun onNextClick() {
        incrementNumberOnNextClicked()
        measureClickInterval()
        incrementExercisePerformed()
    }

    private fun incrementNumberOnNextClicked() {
        numberOnNextCLicked++
    }

    private fun measureClickInterval() {
        val currentTime = Date().time
        val interval = currentTime - previousTime
        previousTime = currentTime
        timeIntervals.add(interval)
    }

    protected fun incrementExercisePerformed() {
        if (exerciseType is ExerciseType.Training) {
            incrementExercisePerformedInteractor(exerciseType.exerciseId)
                .subscribe()
                .let(disposables::add)
        }
    }

    private fun saveStatistics(doOnComplete: () -> Unit) {
        saveStatisticsInteractor(
            exerciseType = exerciseType,
            passedLettersOrWordsCount = numberOnNextCLicked,
            averageTimeOnWord = averageTimeOnWord,
            summaryTimeSpendOnExercise = summaryTimeSpendOnExercise
        )
            .observeOn(AndroidSchedulers.mainThread())
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
        measureClickInterval()

        saveStatistics {
            disposeDisposables()
        }
    }
}