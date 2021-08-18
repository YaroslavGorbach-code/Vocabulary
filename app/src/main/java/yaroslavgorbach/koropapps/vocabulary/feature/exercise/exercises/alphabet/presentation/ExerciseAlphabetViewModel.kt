package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseWordCategory

class ExerciseAlphabetViewModel(application: Application) : AndroidViewModel(application) {

    private val letters: MutableLiveData<List<String>> = MutableLiveData(
        getApplication<Application>().applicationContext.resources.getStringArray(
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

    var lettersCount: Int = -1
        private set

    init {
        refreshLetter()
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

    fun stopTimer() {
        scope.cancel()
        _progress.value = 0
    }

    fun refreshLetter() {
        lettersCount++
        _letter.value = letters.value?.firstOrNull()
            .also { letter -> letters.value = letters.value?.filter { it != letter } }
        startProgressTimer()
    }

    fun getDescriptionText(exerciseName: ExerciseName): String {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES -> {
                getApplication<Application>().getString(R.string.desc_short_alphabet_a)
            }
            ExerciseName.ALPHABET_NOUN -> {
                getApplication<Application>().getString(R.string.desc_short_alphabet_n)
            }
            ExerciseName.ALPHABET_VERBS -> {
                getApplication<Application>().getString(R.string.desc_short_alphabet_v)
            }
            else -> ""
        }
    }
}