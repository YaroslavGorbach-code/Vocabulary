package yaroslavgorbach.koropapps.vocabulary.feature.exercise.antonimssininims

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.usecase.GetWordsFillingsUseCase
import yaroslavgorbach.koropapps.vocabulary.data.exercise.repo.RepoExerciseImp
import kotlin.random.Random

class ExerciseAntonymsSynonymsVm(application: Application) : AndroidViewModel(application) {
    private val text = MutableLiveData<String>()
    private val getWordsFillingsUseCase: GetWordsFillingsUseCase = GetWordsFillingsUseCase(RepoExerciseImp(application))

    fun getText(): LiveData<String> {
        return text
    }

    init {
        generateText()
    }

    fun generateText() {
        when (Random.nextInt(1, 2)) {
            1 -> {
                text.value = getApplication<Application>().applicationContext.getString(
                    R.string.desc_short_antonyms,
                    getWordsFillingsUseCase.invoke().random()
                )
            }
            2 -> {
                text.value = getApplication<Application>().applicationContext.getString(
                    R.string.desc_short_synonyms,
                    getWordsFillingsUseCase.invoke().random()
                )
            }
        }
    }
}