package yaroslavgorbach.koropapps.vocabulary.di.business.statistics

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.statistics.*
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.di.data.statistics.DataModuleStatistics
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di.DescriptionComponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.di.ProfileComponent

@Module(
    includes = [DataModuleStatistics::class],
    subcomponents = [
        DescriptionComponent::class,
        ProfileComponent::class
    ]
)
class BusinessStatisticsModule {
    @Provides
    fun provideInsertValueStatisticsInteractor(
        repoStatistics: RepoStatistics
    ): InsertStatisticValueInteractor {
        return InsertStatisticValueInteractor(repoStatistics)
    }

    @Provides
    fun provideObserveValueStatisticsInteractor(
        repoStatistics: RepoStatistics
    ): ObserveStatisticsValueInteractor {
        return ObserveStatisticsValueInteractor(repoStatistics)
    }

    @Provides
    fun provideInsertTimeStatisticsInteractor(
        repoStatistics: RepoStatistics
    ): InsertStatisticTimeInteractor {
        return InsertStatisticTimeInteractor(repoStatistics)
    }

    @Provides
    fun provideObserveTimeStatisticsInteractor(
        repoStatistics: RepoStatistics
    ): ObserveStatisticsTimeInteractor {
        return ObserveStatisticsTimeInteractor(repoStatistics)
    }

    @Provides
    fun provideObserveStatisticDayInteractor(
        repoStatistics: RepoStatistics
    ): ObserveStatisticDaysInteractor {
        return ObserveStatisticDaysInteractor(repoStatistics)
    }

    @Provides
    fun provideInsertOrUpdateStatisticsInteractor(
        repoStatistics: RepoStatistics,
        provideObserveStatisticDaysInteractor: ObserveStatisticDaysInteractor
    ): InsertOrUpdateStatisticDayInteractor {
        return InsertOrUpdateStatisticDayInteractor(
            repoStatistics,
            provideObserveStatisticDaysInteractor
        )
    }

    @Provides
    fun provideGetStatisticsLevelInteractor(
        repoStatistics: RepoStatistics
    ): GetStatisticsLevelInteractor {
        return GetStatisticsLevelInteractor(repoStatistics)
    }
}