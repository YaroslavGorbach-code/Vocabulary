package yaroslavgorbach.koropapps.vocabulary.di.business.statistics

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticValueInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsTimeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsValueInteractor
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.di.data.statistics.DataModuleStatistics
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di.DescriptionComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.di.AlphabetExerciseComponent

@Module(
    includes = [DataModuleStatistics::class],
    subcomponents = [
        AlphabetExerciseComponent::class,
        DescriptionComponent::class
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
}