package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonimssininims.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseWordCategory
import kotlin.random.Random

class ExerciseAntonymsSynonymsViewModel(application: Application) : AndroidViewModel(application) {

    private val words: List<String>
        get() = getApplication<Application>().applicationContext.resources.getStringArray(
            ExerciseWordCategory.FILLINGS.resId
        ).toList()

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    private val _descriptionText = MutableLiveData<String>()

    val descriptionText: LiveData<String>
        get() = _descriptionText

    init {
        generateDescriptionTextAndNewWord()
    }

    fun generateDescriptionTextAndNewWord() {
        when (Random.nextInt(1, 2)) {
            1 -> {
                _descriptionText.value = getApplication<Application>().applicationContext.getString(
                    R.string.desc_short_antonyms,
                )
            }
            2 -> {
                _descriptionText.value = getApplication<Application>().applicationContext.getString(
                    R.string.desc_short_synonyms,
                )
            }
        }
        generateWord()
    }

    private fun generateWord(){
        _word.value = words.random()
    }
}