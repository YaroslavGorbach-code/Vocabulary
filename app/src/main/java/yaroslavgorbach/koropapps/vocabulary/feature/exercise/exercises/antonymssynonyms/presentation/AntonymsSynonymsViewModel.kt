package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.presentation

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
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseWordCategory
import java.util.*
import javax.inject.Inject

class AntonymsSynonymsViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val insertStatisticValueInteractor: InsertStatisticValueInteractor,
    private val insertStatisticTimeInteractor: InsertStatisticTimeInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val words: List<String>
        get() = application.applicationContext.resources.getStringArray(
            ExerciseWordCategory.FILLINGS.resId
        ).toList()

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val _exercise: MutableLiveData<TrainingExerciseEntity> = MutableLiveData()
        get() {
            if (exerciseType is ExerciseType.Training) {
                observeTrainingExerciseInteractor(exerciseType.exerciseId)
                    .subscribe(field::postValue)
                    .let(disposables::add)
            }
            return field
        }

    val exercise: LiveData<TrainingExerciseEntity>
        get() = _exercise

    private var passedWordsCount: Int = 0

    private val timeIntervals: MutableList<Long> = arrayListOf()

    private var previousTime: Long = Date().time

    private val averageTimeOnWord: Float
        get() = (timeIntervals.sum() / timeIntervals.size) / 1000f

    init {
        generateNewWord()
    }

    fun onNextWordClick() {
        generateNewWord()
        incrementExercisePerformed()
        incrementPassedWords()
        measureClickInterval()
    }

    private fun generateNewWord() {
        _word.value = words.random()
    }

    private fun measureClickInterval() {
        val currentTime = Date().time
        val interval = currentTime - previousTime
        previousTime = currentTime
        timeIntervals.add(interval)
    }

    private fun incrementPassedWords() {
        passedWordsCount++
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