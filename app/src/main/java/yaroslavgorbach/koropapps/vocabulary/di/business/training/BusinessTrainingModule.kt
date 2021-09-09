package yaroslavgorbach.koropapps.vocabulary.di.business.training

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.training.*
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.di.data.training.DataModuleTraining

@Module(includes = [DataModuleTraining::class])
class BusinessTrainingModule {

    @Provides
    fun provideGetTrainingExerciseInteractor(
        repoTraining: RepoTraining
    ): GetTrainingExerciseInteractor {
        return GetTrainingExerciseInteractor(repoTraining)
    }

    @Provides
    fun provideInsertTrainingExercisesInteractor(
        repoTraining: RepoTraining
    ): InsertTrainingExercisesInteractor {
        return InsertTrainingExercisesInteractor(repoTraining)
    }

    @Provides
    fun provideInsertTrainingInteractor(
        repoTraining: RepoTraining
    ): InsertTrainingInteractor {
        return InsertTrainingInteractor(repoTraining)
    }

    @Provides
    fun provideObserveLastFifeTrainingsInteractor(
        observeTrainingsInteractor: ObserveTrainingsInteractor,
        insertTrainingInteractor: InsertTrainingInteractor
    ): ObserveLastFifeTrainingsInteractor {
        return ObserveLastFifeTrainingsInteractor(
            observeTrainingsInteractor,
            insertTrainingInteractor
        )
    }

    @Provides
    fun provideObserveCurrentTrainingWithExercisesInteractor(
        observeLastFifeTrainingsInteractor: ObserveLastFifeTrainingsInteractor,
        insertTrainingExercisesInteractor: InsertTrainingExercisesInteractor
    ): ObserveCurrentTrainingWithExercisesInteractor {
        return ObserveCurrentTrainingWithExercisesInteractor(
            observeLastFifeTrainingsInteractor,
            insertTrainingExercisesInteractor
        )
    }

    @Provides
    fun provideObserveTrainingExerciseInteractor(
        repoTraining: RepoTraining
    ): ObserveTrainingExerciseInteractor {
        return ObserveTrainingExerciseInteractor(repoTraining)
    }

    @Provides
    fun provideObserveTrainingsInteractor(
        repoTraining: RepoTraining
    ): ObserveTrainingsInteractor {
        return ObserveTrainingsInteractor(repoTraining)
    }

    @Provides
    fun provideUpdateTrainingExerciseInteractor(
        repoTraining: RepoTraining
    ): UpdateTrainingExerciseInteractor {
        return UpdateTrainingExerciseInteractor(repoTraining)
    }

    @Provides
    fun provideIncrementExercisePerformedInteractor(
        getTrainingExerciseInteractor: GetTrainingExerciseInteractor,
        updateTrainingExerciseInteractor: UpdateTrainingExerciseInteractor
    ): IncrementExercisePerformedInteractor {
        return IncrementExercisePerformedInteractor(
            getTrainingExerciseInteractor,
            updateTrainingExerciseInteractor
        )
    }

    @Provides
    fun provideGetCurrentTrainingIsFinishedInteractor(
        observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor
    ): GetCurrentTrainingIsFinishedInteractor {
        return GetCurrentTrainingIsFinishedInteractor(observeCurrentTrainingWithExercisesInteractor)
    }
}