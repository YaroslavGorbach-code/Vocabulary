package yaroslavgorbach.koropapps.vocabulary.di.business.records

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.phrase.ObserveTodayPhraseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.records.GetRecordFilesInteractor
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhrase
import yaroslavgorbach.koropapps.vocabulary.data.records.repo.RepoRecords
import yaroslavgorbach.koropapps.vocabulary.di.data.phrase.DataModulePhrase
import yaroslavgorbach.koropapps.vocabulary.di.data.records.DataModuleRecords
import javax.inject.Singleton

@Module(includes = [DataModuleRecords::class])
class BusinessRecordsModule {

    @Singleton
    @Provides
    fun provideGetRecordsInteractor(repoRecords: RepoRecords): GetRecordFilesInteractor {
        return GetRecordFilesInteractor(repoRecords)
    }
}