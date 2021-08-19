package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ten.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ten.ui.TenFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType

@Subcomponent(modules = [TenExerciseModule::class])
interface TenExerciseComponent {
    fun inject(fragment: TenFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): TenExerciseComponent
    }
}