package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticValueInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.common.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class NarratorViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val insertStatisticValueInteractor: InsertStatisticValueInteractor,
    private val insertStatisticTimeInteractor: InsertStatisticTimeInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _numberOfWords = MutableLiveData<String>()

    val numberOfWords: LiveData<String>
        get() = _numberOfWords

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

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private var passedWordsCount: Int = 0

    private val timeIntervals: MutableList<Long> = arrayListOf()

    private var previousTime: Long = Date().time

    private val averageTimeOnWord: Float
        get() {
            return try {
                (timeIntervals.sum() / timeIntervals.size) / 1000f
            } catch (ex: Exception) {
                0f
            }
        }

    init {
        generateWords()
    }

    fun onNextClick() {
        generateWords()
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

    private fun generateWords() {
        _numberOfWords.value = Random.nextInt(3, 15).toString()
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