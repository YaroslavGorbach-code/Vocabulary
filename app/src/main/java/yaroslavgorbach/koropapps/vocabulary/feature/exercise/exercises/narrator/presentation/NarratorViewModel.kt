package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseType
import javax.inject.Inject
import kotlin.random.Random

class NarratorViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val insertStatisticInteractor: InsertStatisticInteractor
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

    private var passedWordsCount: Int = 0

    init {
        generateWords()
    }

    // TODO: 8/26/2021 move description to exercise name model
    fun getDescriptionText(exName: ExerciseName): String {
        return when (exName) {
            ExerciseName.NARRATOR_NOUN -> {
                application.applicationContext.getString(R.string.desc_short_narrator_noun)
            }
            ExerciseName.NARRATOR_ADJECTIVES -> {
                application.applicationContext.getString(R.string.desc_short_narrator_adjectives)
            }
            ExerciseName.NARRATOR_VERBS -> {
                application.applicationContext.getString(R.string.desc_short_narrator_verbs)
            }
            else -> ""
        }
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

    private fun saveStatistics() {
        insertStatisticInteractor.invoke(
            StatisticsEntityFactory().create(exerciseType.getExerciseName(), passedWordsCount)
        )
            .observeOn(AndroidSchedulers.mainThread())
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
        saveStatistics()
        disposeDisposables()
    }

}