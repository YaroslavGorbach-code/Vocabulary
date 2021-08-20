package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.training.GetTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.UpdateTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseWordCategory
import javax.inject.Inject

class AlphabetViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val updateTrainingExerciseInteractor: UpdateTrainingExerciseInteractor,
    private val getTrainingExerciseInteractor: GetTrainingExerciseInteractor,
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
            return when (exerciseType) {
                is ExerciseType.Common -> {
                    when (exerciseType.name) {
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
                is ExerciseType.Training -> {
                    when (exerciseType.name) {
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
            }
        }

    var lettersCount: Int = -1
        private set

    init {
        refreshLetter()
    }

    fun stopTimer() {
        scope.cancel()
        _progress.value = 0
        incrementExercisePerformed()
    }

    fun refreshLetter() {
        lettersCount++
        _letter.value = letters.value?.firstOrNull()
            .also { letter -> letters.value = letters.value?.filter { it != letter } }
        startProgressTimer()
    }

    private fun incrementExercisePerformed() {
        if (exerciseType is ExerciseType.Training) {
            getTrainingExerciseInteractor(exerciseType.exerciseId)
                .doOnSuccess { it.performed++ }
                .flatMapCompletable(updateTrainingExerciseInteractor::invoke)
                .subscribe()
                .let(disposables::add)
        }
    }

    private fun startProgressTimer() {
        scope.coroutineContext.cancelChildren()
        scope.launch {
            (0..100).forEach {
                delay(50)
                _progress.value = it
            }
        }
    }
}