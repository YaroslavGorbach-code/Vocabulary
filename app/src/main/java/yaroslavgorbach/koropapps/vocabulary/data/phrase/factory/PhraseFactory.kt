package yaroslavgorbach.koropapps.vocabulary.data.phrase.factory

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import java.util.*
import kotlin.random.Random

class PhraseFactory {
    fun create(context: Context): Phrase {

        val phrasesResArray = context.resources.getStringArray(R.array.phrases)
        val phrasesExplanationsResArray =
            context.resources.getStringArray(R.array.phrases_explanations)

        val randomIndex: Int = Random.nextInt(phrasesResArray.size)

        return Phrase(
            phrase = phrasesResArray[randomIndex],
            explanation = phrasesExplanationsResArray[randomIndex],
            date = Date()
        )
    }
}