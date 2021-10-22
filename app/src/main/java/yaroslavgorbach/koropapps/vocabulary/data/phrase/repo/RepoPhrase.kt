package yaroslavgorbach.koropapps.vocabulary.data.phrase.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase

interface RepoPhrase {

    suspend fun createInsertAndGet(): Phrase

    suspend fun insert(phrase: Phrase)

    fun observe(): Flow<Phrase>
}