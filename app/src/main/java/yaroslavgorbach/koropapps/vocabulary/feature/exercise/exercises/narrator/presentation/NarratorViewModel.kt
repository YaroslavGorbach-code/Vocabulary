package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticValueInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.common.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import javax.inject.Inject
import kotlin.random.Random

class NarratorViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val insertStatisticValueInteractor: InsertStatisticValueInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _numberOfWords = MutableLiveData<String>()

    val numberOfWords: LiveData<String>
        get() = _numberOfWords

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

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private var passedWordsCount: Int = 0

    init {
        generateWords()
    }

    fun onNextClick() {
        generateWords()
        incrementExercisePerformed()
        incrementPassedWords()
    }

    private fun incrementPassedWords() {
        passedWordsCount++
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
            StatisticsEntityFactory().create(exerciseType.getExerciseName(), passedWordsCount)
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