package yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetLettersUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp

class ExerciseAlphabetVm(application: Application) : AndroidViewModel(application) {
    private val letters = MutableLiveData(GetLettersUseCase(RepoExerciseImp(application)).invoke())
    private val currentLetter = MutableLiveData<String>()

    fun getLetter(): LiveData<String> {
        return currentLetter
    }

    init {
        setNewLetter()
    }

    fun setNewLetter() {
        currentLetter.value = letters.value?.first()
            .also { letter -> letters.value = letters.value?.filter { it != letter } }
    }
}