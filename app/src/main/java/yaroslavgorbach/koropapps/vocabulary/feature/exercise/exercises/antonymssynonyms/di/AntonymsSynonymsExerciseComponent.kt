package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.ui.AntonymsSynonymsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.di.AssociationsExerciseModule
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType

@Subcomponent(modules = [AntonymsSynonymsExerciseModule::class])
interface AntonymsSynonymsExerciseComponent {

    fun inject(antonymsSynonymsFragment: AntonymsSynonymsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): AntonymsSynonymsExerciseComponent
    }
}