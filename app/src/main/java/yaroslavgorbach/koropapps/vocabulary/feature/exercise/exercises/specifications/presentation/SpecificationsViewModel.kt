package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.specifications.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertOrUpdateStatisticDayInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticValueInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseWordCategory
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import java.util.*
import javax.inject.Inject

class SpecificationsViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val insertStatisticValueInteractor: InsertStatisticValueInteractor,
    private val insertStatisticTimeInteractor: InsertStatisticTimeInteractor,
    private val insertOrUpdateStatisticDayInteractor: InsertOrUpdateStatisticDayInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val words: List<String>
        get() = application.applicationContext.resources.getStringArray(
            ExerciseWordCategory.NOUNS.resId
        ).toList()

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

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

    private var passedWordsCount: Int = 0

    private val timeIntervals: MutableList<Long> = arrayListOf()

    private var previousTime: Long = Date().time

    private val summaryTimeSpendOnExercise: Long
        get() = timeIntervals.sum()

    private val averageTimeOnWord: Float
        get() {
            return try {
                (summaryTimeSpendOnExercise / timeIntervals.size) / 1000f
            } catch (ex: Exception) {
                0f
            }
        }

    init {
        generateWord()
    }

    fun onNextClick() {
        generateWord()
        incrementExercisePerformed()
        incrementPassedWords()
        measureClickInterval()
    }

    private fun incrementPassedWords() {
        passedWordsCount++
    }

    private fun measureClickInterval() {
        val currentTime = Date().time
        val interval = currentTime - previousTime
        previousTime = currentTime
        timeIntervals.add(interval)
    }

    private fun generateWord() {
        _word.value = words.random()
    }

    private fun incrementExercisePerformed() {
        if (exerciseType is ExerciseType.Training) {
            incrementExercisePerformedInteractor(exerciseType.exerciseId)
                .subscribe()
                .let(disposables::add)
        }
    }

    private fun saveStatistics(doOnComplete: () -> Unit) {
        insertStatisticValueInteractor.invoke(
            StatisticsEntityFactory().createValueEntity(
                exerciseType.getExerciseName(),
                passedWordsCount
            )
        ).andThen(
            insertStatisticTimeInteractor.invoke(
                StatisticsEntityFactory().createTimeEntity(
                    exerciseType.getExerciseName(),
                    averageTimeOnWord
                )
            )
        ).andThen(
            insertOrUpdateStatisticDayInteractor(
                StatisticsEntityFactory().createDayEntity(summaryTimeSpendOnExercise)
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