package yaroslavgorbach.koropapps.vocabulary.di.business.records

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.phrase.ObserveTodayPhraseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.records.DeleteAllRecordsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.records.DeleteRecordFileInteractor
import yaroslavgorbach.koropapps.vocabulary.business.records.GetRecordFilesInteractor
import yaroslavgorbach.koropapps.vocabulary.data.phrase.repo.RepoPhrase
import yaroslavgorbach.koropapps.vocabulary.data.records.repo.RepoRecords
import yaroslavgorbach.koropapps.vocabulary.di.data.phrase.DataModulePhrase
import yaroslavgorbach.koropapps.vocabulary.di.data.records.DataModuleRecords
import javax.inject.Singleton

@Module(includes = [DataModuleRecords::class])
class BusinessRecordsModule {

    @Provides
    fun provideGetRecordsInteractor(repoRecords: RepoRecords): GetRecordFilesInteractor {
        return GetRecordFilesInteractor(repoRecords)
    }

    @Provides
    fun provideDeleteRecordInteractor(repoRecords: RepoRecords): DeleteRecordFileInteractor {
        return DeleteRecordFileInteractor(repoRecords)
    }

    @Provides
    fun provideDeleteAllRecordsInteractor(repoRecords: RepoRecords): DeleteAllRecordsInteractor {
        return DeleteAllRecordsInteractor(repoRecords)
    }
}
