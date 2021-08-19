package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.ui.AntonymsSynonymsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.ui.AssociationsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType

@Subcomponent(modules = [AssociationsExerciseModule::class])
interface AssociationsExerciseComponent {

    fun inject(associationsFragment: AssociationsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): AssociationsExerciseComponent
    }
}
