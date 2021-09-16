package yaroslavgorbach.koropapps.vocabulary.data.phrase.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase

interface RepoPhrase {

    suspend fun insert(context: Context, phrase: Phrase)

    fun observe(context: Context): Flow<Phrase>
}