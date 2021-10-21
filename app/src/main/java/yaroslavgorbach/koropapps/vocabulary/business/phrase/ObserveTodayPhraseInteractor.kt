package yaroslavgorbach.koropapps.vocabulary.business.phrase

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.business.phrase.factory.PhraseFactory
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhrase
import yaroslavgorbach.koropapps.vocabulary.utils.isToday


class ObserveTodayPhraseInteractor(private val repoPhrase: RepoPhrase) {

    // TODO: 18.10.2021 move logic of using factory to data package
    suspend operator fun invoke(context: Context): Flow<Phrase> {
        return repoPhrase.observe(context).map { currentPhrase ->
            if (currentPhrase.date.isToday()) {
                currentPhrase
            } else {
                val newPhrase = PhraseFactory().create(context)
                repoPhrase.insert(context, newPhrase)
                newPhrase
            }
        }
    }
}