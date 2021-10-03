package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.communication.abbreviations.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.communication.abbreviations.ui.AbbreviationsFragment

@Subcomponent(modules = [AbbreviationExerciseModule::class])
interface AbbreviationsExerciseComponent {

    fun inject(fragment: AbbreviationsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): AbbreviationsExerciseComponent
    }
}