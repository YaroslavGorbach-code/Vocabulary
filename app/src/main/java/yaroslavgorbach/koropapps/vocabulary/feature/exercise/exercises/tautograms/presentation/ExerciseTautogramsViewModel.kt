package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseWordCategory

class ExerciseTautogramsViewModel(application: Application) : AndroidViewModel(application) {

    private val words: List<String>
        get() = getApplication<Application>().applicationContext.resources.getStringArray(
            ExerciseWordCategory.LETTERS.resId
        ).toList()

    val descriptionText: String
        get() = getApplication<Application>().getString(R.string.desc_short_tautograms)

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    init {
        generateWord()
    }

    fun generateWord() {
        _word.value = words.random()
    }
}