package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseWordCategory

class ExerciseRememberAllViewModel(application: Application) : AndroidViewModel(application) {

    private val words: List<String>
        get() = getApplication<Application>().applicationContext.resources.getStringArray(
            ExerciseWordCategory.LETTERS.resId
        ).toList()

    val descriptionText: String
        get() = getApplication<Application>().applicationContext.getString(
            R.string.desc_short_remember_all,
        )

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    private val _anim = MutableLiveData<Int>()

    val anim: LiveData<Int>
        get() = _anim

    private val _performed = MutableLiveData<Int>()

    val performed: LiveData<Int>
        get() = _performed

    init {
        generateWord()
    }

    fun generateWord() {
        _word.value = words.random()
    }
}