package yaroslavgorbach.koropapps.vocabulary.feature.exercise.narrator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import kotlin.random.Random

class ExerciseNarratorVm(private val app: Application) : AndroidViewModel(app) {
    private val text = MutableLiveData<String>()

    fun getText(): LiveData<String> {
        return text
    }

    fun generateText(exName: ExerciseName) {
        val randomNumber = Random.nextInt(3, 15)
        when (exName) {
            ExerciseName.NARRATOR_NOUN -> {
                text.value = app.applicationContext.getString(R.string.narrator_noun_text, randomNumber)
            }
            ExerciseName.NARRATOR_ADJECTIVES -> {
                text.value = app.applicationContext.getString(R.string.narrator_adjectives_text, randomNumber)
            }
            ExerciseName.NARRATOR_VERBS -> {
                text.value = app.applicationContext.getString(R.string.narrator_verbs_text, randomNumber)
            }
            else -> {}
        }

    }

}