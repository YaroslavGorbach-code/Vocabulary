package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui.DescriptionFragment

@Subcomponent(modules = [DescriptionModule::class])
interface DescriptionComponent {

    fun inject(descriptionFragment: DescriptionFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseName: ExerciseName): DescriptionComponent
    }
}
