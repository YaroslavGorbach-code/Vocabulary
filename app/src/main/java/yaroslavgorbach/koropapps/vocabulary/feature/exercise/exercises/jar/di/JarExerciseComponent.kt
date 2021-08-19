package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.ui.JarFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType

@Subcomponent(modules = [JarExerciseModule::class])
interface JarExerciseComponent {

    fun inject(gameFragment: JarFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): JarExerciseComponent
    }

}