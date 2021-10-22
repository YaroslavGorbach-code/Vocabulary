package yaroslavgorbach.koropapps.vocabulary.business.phrase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhrase
import yaroslavgorbach.koropapps.vocabulary.utils.isToday


class ObserveTodayPhraseInteractor(private val repoPhrase: RepoPhrase) {

    suspend operator fun invoke(): Flow<Phrase> {
        return repoPhrase.observe().map { currentPhrase ->
            if (currentPhrase.date.isToday()) {
                currentPhrase
            } else {
                repoPhrase.createInsertAndGet()
            }
        }
    }
}