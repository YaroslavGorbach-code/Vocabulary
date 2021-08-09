package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import kotlin.random.Random

class ExerciseNarratorViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _numberOfWords = MutableLiveData<String>()

    val numberOfWords: LiveData<String>
        get() = _numberOfWords

    init {
        generateNumberOfWords()
    }

    fun getDescriptionText(exName: ExerciseName): String {
        return when (exName) {
            ExerciseName.NARRATOR_NOUN -> {
                app.applicationContext.getString(R.string.desc_short_narrator_noun)
            }
            ExerciseName.NARRATOR_ADJECTIVES -> {
                app.applicationContext.getString(R.string.desc_short_narrator_adjectives)
            }
            ExerciseName.NARRATOR_VERBS -> {
                app.applicationContext.getString(R.string.desc_short_narrator_verbs)
            }
            else -> ""
        }
    }

    fun generateNumberOfWords() {
        _numberOfWords.value = Random.nextInt(3, 15).toString()
    }

}