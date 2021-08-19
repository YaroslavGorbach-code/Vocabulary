package yaroslavgorbach.koropapps.vocabulary.di.business.training

import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.training.*
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.di.data.training.DataModuleTraining
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.di.AntonymsSynonymsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.di.AssociationsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.game.di.GameExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.di.JarExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.training.di.TrainingComponent

@Module(
    includes = [DataModuleTraining::class],
    subcomponents = [
        AssociationsExerciseComponent::class,
        TrainingComponent::class,
        AntonymsSynonymsExerciseComponent::class,
        GameExerciseComponent::class,
        JarExerciseComponent::class
    ]
)
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
}