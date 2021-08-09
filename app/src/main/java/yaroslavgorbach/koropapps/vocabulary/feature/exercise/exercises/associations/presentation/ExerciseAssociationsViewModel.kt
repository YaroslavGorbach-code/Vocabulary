package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercise.model.ExerciseWordCategory


class ExerciseAssociationsViewModel(application: Application) : AndroidViewModel(application) {

    private val words: List<String>
        get() = getApplication<Application>().applicationContext.resources.getStringArray(
            ExerciseWordCategory.NOUNS.resId
        ).toList()


    val descriptionText: String
        get() = getApplication<Application>().applicationContext.getString(
            R.string.desc_short_associations
        )

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