package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.jar.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.jar.ui.JarFragment

@Subcomponent(modules = [JarExerciseModule::class])
interface JarExerciseComponent {

    fun inject(gameFragment: JarFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): JarExerciseComponent
    }

}