package yaroslavgorbach.koropapps.vocabulary.feature.exercise.jar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.GetLettersUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp

class ExerciseJarVm(application: Application) : AndroidViewModel(application) {
    private val text = MutableLiveData<String>()
    private val getLettersUseCase: GetLettersUseCase =
        GetLettersUseCase(RepoExerciseImp(application))

    fun getText(): LiveData<String> {
        return text
    }

    init {
        generateText()
    }

    fun generateText() {
        text.value = getApplication<Application>().applicationContext.getString(
            R.string.desc_short_three_liter_jar,
            getLettersUseCase.invoke().random()
        )
    }
}