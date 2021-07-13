package yaroslavgorbach.koropapps.vocabulary.feature.exercise.lists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetWordsCategoryUseCase
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetWordsNounsUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp

class ExerciseListsVm(application: Application) : AndroidViewModel(application) {
    private val text = MutableLiveData<String>()
    private val getWordsCategoryUseCase: GetWordsCategoryUseCase =
        GetWordsCategoryUseCase(RepoExerciseImp(application))

    fun getText(): LiveData<String> {
        return text
    }

    init {
        generateText()
    }

    fun generateText() {
        text.value = getApplication<Application>().applicationContext.getString(
            R.string.lists_text,
            getWordsCategoryUseCase.invoke().random()
        )
    }
}