package yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetLettersUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class ExerciseAlphabetVm(application: Application) : AndroidViewModel(application) {
    private val letters = MutableLiveData(GetLettersUseCase(RepoExerciseImp(application)).invoke())
    private val currentLetter = MutableLiveData<String>()
    private val progress = MutableLiveData<Int>()
    private val processScope = CoroutineScope(viewModelScope.coroutineContext)
    private var lettersCount: Int = -1

    fun getLetter(): LiveData<String> {
        return currentLetter
    }

    init {
        setNewLetter()
    }

    fun getText(exerciseName: ExerciseName): String {
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

    fun setNewLetter() {
        lettersCount++
        currentLetter.value = letters.value?.firstOrNull()
            .also { letter -> letters.value = letters.value?.filter { it != letter } }
        startProgressTimer()
    }

    private fun startProgressTimer() {
        processScope.coroutineContext.cancelChildren()
        processScope.launch {
            (0..100).forEach {
                delay(50)
                progress.value = it
            }
        }
    }

    fun getProgress(): LiveData<Int> = progress
    fun getNumberOfLetters() = lettersCount
    fun stopTimer(){
        processScope.cancel()
        progress.value = 0
    }

}