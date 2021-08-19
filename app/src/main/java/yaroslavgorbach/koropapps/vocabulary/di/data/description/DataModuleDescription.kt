package yaroslavgorbach.koropapps.vocabulary.di.data.description

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescriptionImp
import javax.inject.Singleton

@Module
class DataModuleDescription {
    @Singleton
    @Provides
    fun provideRepoDescription(): RepoDescription {
        return RepoDescriptionImp()
    }
}