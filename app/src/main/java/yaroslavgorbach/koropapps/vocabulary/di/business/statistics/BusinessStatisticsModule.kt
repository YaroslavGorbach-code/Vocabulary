package yaroslavgorbach.koropapps.vocabulary.di.business.statistics

import dagger.Module
import yaroslavgorbach.koropapps.vocabulary.di.data.statistics.DataModuleStatistics
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di.DescriptionComponent

@Module(
    includes = [DataModuleStatistics::class],
    subcomponents = [
        DescriptionComponent::class
    ]
)
class BusinessStatisticsModule {

}