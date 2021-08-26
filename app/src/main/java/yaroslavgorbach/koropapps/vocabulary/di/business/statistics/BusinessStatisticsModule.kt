package yaroslavgorbach.koropapps.vocabulary.di.business.statistics

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.statistics.InsertStatisticInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticsInteractor
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
    fun provideInsertStatisticsInteractor(
        repoStatistics: RepoStatistics
    ): InsertStatisticInteractor {
        return InsertStatisticInteractor(repoStatistics)
    }

    @Provides
    fun provideObserveStatisticsInteractor(
        repoStatistics: RepoStatistics
    ): ObserveStatisticsInteractor {
        return ObserveStatisticsInteractor(repoStatistics)
    }
}