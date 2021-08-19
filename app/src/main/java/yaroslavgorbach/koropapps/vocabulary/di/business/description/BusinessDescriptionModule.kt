package yaroslavgorbach.koropapps.vocabulary.di.business.description

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.description.GetDescriptionInteractor
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.di.data.description.DataModuleDescription
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di.DescriptionComponent
import javax.inject.Singleton

@Module(
    includes = [DataModuleDescription::class],
    subcomponents = [DescriptionComponent::class]
)
class BusinessDescriptionModule {
    @Singleton
    @Provides
    fun provideGetDescriptionInteractor(repoDescription: RepoDescription): GetDescriptionInteractor {
        return GetDescriptionInteractor(repoDescription)
    }
}