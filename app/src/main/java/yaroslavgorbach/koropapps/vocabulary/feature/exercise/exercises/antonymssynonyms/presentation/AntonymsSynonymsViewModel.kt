package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.presentation

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
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseWordCategory
import javax.inject.Inject
import kotlin.random.Random

class AntonymsSynonymsViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val insertStatisticInteractor: InsertStatisticInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val words: List<String>
        get() = application.applicationContext.resources.getStringArray(
            ExerciseWordCategory.FILLINGS.resId
        ).toList()

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    private val _descriptionText = MutableLiveData<String>()

    val descriptionText: LiveData<String>
        get() = _descriptionText

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
        generateDescription()
        generateNewWord()
    }

    fun onNextWordClick() {
        generateDescription()
        generateNewWord()
        incrementExercisePerformed()
        incrementPassedWords()
    }

    private fun generateDescription() {
        when (Random.nextInt(1, 2)) {
            1 -> {
                _descriptionText.value = application.applicationContext.getString(
                    R.string.desc_short_antonyms,
                )
            }
            2 -> {
                _descriptionText.value = application.applicationContext.getString(
                    R.string.desc_short_synonyms,
                )
            }
        }
    }

    private fun generateNewWord() {
        _word.value = words.random()
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