package yaroslavgorbach.koropapps.vocabulary.di.business.phrase

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.phrase.ObserveTodayPhraseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhrase
import yaroslavgorbach.koropapps.vocabulary.di.data.phrase.DataModulePhrase
import javax.inject.Singleton

@Module(includes = [DataModulePhrase::class])
class BusinessPhraseModule {
    @Provides
    fun provideObserveTodayPhraseInteractor(repoPhrase: RepoPhrase): ObserveTodayPhraseInteractor {
        return ObserveTodayPhraseInteractor(repoPhrase)
    }
}
