package yaroslavgorbach.koropapps.vocabulary.data.phrase.local

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase

interface PhraseDataStore {
    suspend fun insert(context: Context, phrase: Phrase)

    fun observe(context: Context): Flow<Phrase>
}