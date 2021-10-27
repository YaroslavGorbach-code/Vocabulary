package yaroslavgorbach.koropapps.vocabulary.di.business.statistics

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.*
import yaroslavgorbach.koropapps.vocabulary.business.training.GetCurrentTrainingIsFinishedInteractor
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics
import yaroslavgorbach.koropapps.vocabulary.di.business.achievements.BusinessAchievementsModule
import yaroslavgorbach.koropapps.vocabulary.di.data.statistics.DataModuleStatistics

@Module(includes = [DataModuleStatistics::class])
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
        repoStatistics: RepoStatistics,
    ): GetStatisticsCommonInfoInteractor {
        return GetStatisticsCommonInfoInteractor(repoStatistics)
    }

    @Provides
    fun provideGetAllExercisesStatisticiansInteractor(
        repoStatistics: RepoStatistics,
    ): GetAllExercisesStatisticsValueInteractor {
        return GetAllExercisesStatisticsValueInteractor(repoStatistics)
    }

    @Provides
    fun provideUpdateStatisticsLevelInteractor(
        repoStatistics: RepoStatistics,
        getCurrentTrainingIsFinishedInteractor: GetCurrentTrainingIsFinishedInteractor,
        getStatisticsCommonInfoInteractor: GetStatisticsCommonInfoInteractor
    ): UpdateStatisticsCommonInfoInteractor {
        return UpdateStatisticsCommonInfoInteractor(
            getStatisticsCommonInfoInteractor,
            getCurrentTrainingIsFinishedInteractor,
            repoStatistics
        )
    }

    @Provides
    fun provideSaveStatisticsInteractor(
        insertStatisticTimeInteractor: InsertStatisticTimeInteractor,
        insertStatisticValueInteractor: InsertStatisticValueInteractor,
        insertOrUpdateStatisticDayInteractor: InsertOrUpdateStatisticDayInteractor,
        updateStatisticDayInteractor: UpdateStatisticsCommonInfoInteractor,
        achieveAchievementInteractor: AchieveAchievementInteractor
    ): SaveStatisticsInteractor {
        return SaveStatisticsInteractor(
            insertStatisticValueInteractor = insertStatisticValueInteractor,
            insertStatisticTimeInteractor = insertStatisticTimeInteractor,
            insertOrUpdateStatisticDayInteractor = insertOrUpdateStatisticDayInteractor,
            updateStatisticsCommonInfoInteractor = updateStatisticDayInteractor,
        )
    }
}