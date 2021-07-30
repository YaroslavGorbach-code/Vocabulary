package yaroslavgorbach.koropapps.vocabulary.feature.exercise.tautograms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.GetLettersUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp

class ExerciseTautogramsVm(application: Application) : AndroidViewModel(application) {
    private val letters = MutableLiveData(GetLettersUseCase(RepoExerciseImp(application)).invoke())
    private val currentText = MutableLiveData<String>()

    fun getLetter(): LiveData<String> {
        return currentText
    }

    init {
        setRandomLetter()
    }

    fun getText(): String{
        return getApplication<Application>().getString(R.string.desc_short_tautograms)
    }

    fun setRandomLetter() {
        currentText.value = letters.value?.random()
    }
}