package yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yaroslavgorbach.koropapps.vocabulary.business.exercise.alphabet.model.Letter
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetLettersUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp

class ExerciseAlphabetVm : ViewModel() {
    private val letters = MutableLiveData(GetLettersUseCase(RepoExerciseImp()).invoke())
    private val currentLetter = MutableLiveData<Letter>()
    fun getLetter(): LiveData<Letter> {
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