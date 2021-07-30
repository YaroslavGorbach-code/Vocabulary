package yaroslavgorbach.koropapps.vocabulary.data.exercise.repo

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R

class RepoExerciseImp(context: Context) : RepoExercise {
    private val letters = context.resources.getStringArray(R.array.letters).toList()
    private val wordsFillings = context.resources.getStringArray(R.array.fillings).toList()
    private val wordsNouns = context.resources.getStringArray(R.array.nouns).toList()
    private val wordsCategory = context.resources.getStringArray(R.array.categories).toList()

    override fun getLetters(): List<String> {
        return letters
    }

    override fun getWordsFillings(): List<String> {
        return wordsFillings
    }

    override fun getWordsNouns(): List<String> {
        return wordsNouns
    }

    override fun getWordsCategory(): List<String> {
        return wordsCategory
    }
}