package yaroslavgorbach.koropapps.vocabulary.data.exercise.repo

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercise.repo.RepoExercise

class RepoExerciseImp(context: Context) : RepoExercise {
    private val letters = context.resources.getStringArray(R.array.letters).toList()
    private val words = context.resources.getStringArray(R.array.words).toList()

    override fun getLetters(): List<String> {
        return letters
    }

    override fun getWords(): List<String> {
        return words
    }
}