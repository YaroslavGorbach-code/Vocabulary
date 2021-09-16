package yaroslavgorbach.koropapps.vocabulary.data.phrase.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.PhraseDataStore
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase

class RepoPhraseImp(private val localDataSource: PhraseDataStore) : RepoPhrase {

    override suspend fun insert(context: Context, phrase: Phrase) {
        localDataSource.insert(context, phrase)
    }

    override fun observe(context: Context): Flow<Phrase> = localDataSource.observe(context)
}