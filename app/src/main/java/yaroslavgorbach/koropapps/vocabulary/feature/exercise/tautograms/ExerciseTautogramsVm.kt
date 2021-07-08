package yaroslavgorbach.koropapps.vocabulary.feature.exercise.tautograms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yaroslavgorbach.koropapps.vocabulary.business.exercise.model.Letter
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetLettersUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp

class ExerciseTautogramsVm : ViewModel() {
    private val letters = MutableLiveData(GetLettersUseCase(RepoExerciseImp()).invoke())
    private val currentLetter = MutableLiveData<Letter>()

    fun getLetter(): LiveData<Letter> {
        return currentLetter
    }

    init {
        setRandomLetter()
    }

    fun setRandomLetter() {
        currentLetter.value = letters.value?.random()
    }
}