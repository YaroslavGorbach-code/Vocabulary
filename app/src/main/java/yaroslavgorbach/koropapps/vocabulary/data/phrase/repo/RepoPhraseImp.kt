package yaroslavgorbach.koropapps.vocabulary.data.phrase.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.phrase.factory.PhraseFactory
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.PhraseDataStore
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase

class RepoPhraseImp(private val localDataSource: PhraseDataStore, private val context: Context) :
    RepoPhrase {

    override suspend fun insert(phrase: Phrase) {
        localDataSource.insert(context, phrase)
    }

    override suspend fun createInsertAndGet(): Phrase {
        val newPhrase = PhraseFactory().create(context)
        insert(newPhrase)

        return newPhrase
    }

    override fun observe(): Flow<Phrase> = localDataSource.observe(context)
}