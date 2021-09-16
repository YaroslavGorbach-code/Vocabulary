package yaroslavgorbach.koropapps.vocabulary.business.phrase.factory

import android.content.Context
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import java.util.*

class PhraseFactory {
    fun create(context: Context): Phrase{
        // TODO: 9/15/2021 replace letters on phrases
        val phrasesResArray = context.resources.getStringArray(R.array.letters)
        return Phrase(phrasesResArray.random(), Date())
    }
}