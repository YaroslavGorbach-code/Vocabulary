package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.specifications.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.specifications.ui.SpecificationsFragment

@Subcomponent(modules = [SpecificationsExerciseModule::class])
interface SpecificationsExerciseComponent {

    fun inject(fragment: SpecificationsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): SpecificationsExerciseComponent
    }
}