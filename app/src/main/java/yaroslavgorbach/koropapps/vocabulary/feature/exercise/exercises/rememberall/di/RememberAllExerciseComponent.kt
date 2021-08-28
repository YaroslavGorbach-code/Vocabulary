package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.ui.RememberAllFragment
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

@Subcomponent(modules = [RememberAllExerciseModule::class])
interface RememberAllExerciseComponent {

    fun inject(fragment: RememberAllFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): RememberAllExerciseComponent
    }
}