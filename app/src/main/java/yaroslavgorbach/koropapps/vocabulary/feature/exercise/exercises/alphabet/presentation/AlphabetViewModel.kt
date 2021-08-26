package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.factory.StatisticsEntityFactory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model.ExerciseWordCategory
import javax.inject.Inject

class AlphabetViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val insertStatisticInteractor: InsertStatisticInteractor,
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
        get() {
            // TODO: 8/24/2021 move description to exercise type model
            return when (exerciseType.getExerciseName()) {
                ExerciseName.ALPHABET_ADJECTIVES -> {
                    application.getString(R.string.desc_short_alphabet_a)
                }
                ExerciseName.ALPHABET_NOUN -> {
                    application.getString(R.string.desc_short_alphabet_n)
                }
                ExerciseName.ALPHABET_VERBS -> {
                    application.getString(R.string.desc_short_alphabet_v)
                }
                else -> ""
            }
        }

    var passedLettersCount: Int = 0
        private set

    init {
        refreshLetter()
        refreshProgressTimer()
    }

    fun onNextLetterClick() {
        refreshLetter()
        incrementPassedLettersCount()
        refreshProgressTimer()
    }

    fun onTimerFinished() {
        incrementExercisePerformed()
        scope.cancel()
        _progress.value = 0
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

    private fun saveStatistics() {
        insertStatisticInteractor.invoke(
            StatisticsEntityFactory().create(exerciseType.getExerciseName(), passedLettersCount)
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