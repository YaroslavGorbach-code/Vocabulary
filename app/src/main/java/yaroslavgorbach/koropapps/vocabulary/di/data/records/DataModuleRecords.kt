package yaroslavgorbach.koropapps.vocabulary.di.data.records

import android.app.Application
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.PhraseDataStore
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.PhraseDataStoreImp
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhrase
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhraseImp
import yaroslavgorbach.koropapps.vocabulary.data.records.repo.RepoRecords
import yaroslavgorbach.koropapps.vocabulary.data.records.repo.RepoRecordsImp
import javax.inject.Singleton

@Module
class DataModuleRecords {

    @Singleton
    @Provides
    fun provideRepoRecords(context: Application): RepoRecords {
        return RepoRecordsImp(context)
    }
}