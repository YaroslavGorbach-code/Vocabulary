package yaroslavgorbach.koropapps.vocabulary.feature.exercise.associations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetWordsNounsUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp

class ExerciseAssociationsVm(application: Application): AndroidViewModel(application) {
    private val text = MutableLiveData<String>()
    private val getWordsNounsUseCase: GetWordsNounsUseCase =
        GetWordsNounsUseCase(RepoExerciseImp(application))

    fun getText(): LiveData<String> {
        return text
    }

    init {
        generateText()
    }

    fun generateText() {
        text.value = getApplication<Application>().applicationContext.getString(
            R.string.associations_text,
            getWordsNounsUseCase.invoke().random()
        )
    }
}