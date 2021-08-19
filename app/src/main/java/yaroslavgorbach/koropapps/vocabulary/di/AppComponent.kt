package yaroslavgorbach.koropapps.vocabulary.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import yaroslavgorbach.koropapps.vocabulary.di.business.description.BusinessDescriptionModule
import yaroslavgorbach.koropapps.vocabulary.di.business.exercises.BusinessExercisesModule
import yaroslavgorbach.koropapps.vocabulary.di.business.training.BusinessTrainingModule
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di.DescriptionComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.di.AntonymsSynonymsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.di.AssociationsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.game.di.GameExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.di.ExercisesListComponent
import yaroslavgorbach.koropapps.vocabulary.feature.training.di.TrainingComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        BusinessTrainingModule::class,
        BusinessExercisesModule::class,
        BusinessDescriptionModule::class
    ]
)
interface AppComponent {

    fun antonymsSynonymsComponent(): AntonymsSynonymsExerciseComponent.Factory

    fun exercisesListComponent(): ExercisesListComponent.Factory

    fun trainingComponent(): TrainingComponent.Factory

    fun descriptionComponent(): DescriptionComponent.Factory

    fun associationsComponent(): AssociationsExerciseComponent.Factory

    fun gameComponent(): GameExerciseComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): AppComponent
    }
}