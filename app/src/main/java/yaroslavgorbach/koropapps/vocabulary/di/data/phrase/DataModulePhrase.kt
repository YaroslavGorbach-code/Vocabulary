package yaroslavgorbach.koropapps.vocabulary.di.data.phrase

import android.app.Application
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.PhraseDataStore
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.PhraseDataStoreImp
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhrase
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhraseImp
import javax.inject.Singleton

@Module
class DataModulePhrase {

    @Singleton
    @Provides
    fun providePhraseDataStore(): PhraseDataStore {
        return PhraseDataStoreImp()
    }

    @Singleton
    @Provides
    fun provideRepoPhrase(phraseDataStore: PhraseDataStore, context: Application): RepoPhrase {
        return RepoPhraseImp(phraseDataStore, context)
    }
}