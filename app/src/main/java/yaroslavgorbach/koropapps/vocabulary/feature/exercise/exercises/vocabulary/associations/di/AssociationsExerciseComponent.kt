package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.associations.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.associations.ui.AssociationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

@Subcomponent(modules = [AssociationsExerciseModule::class])
interface AssociationsExerciseComponent {

    fun inject(associationsFragment: AssociationsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): AssociationsExerciseComponent
    }
}
